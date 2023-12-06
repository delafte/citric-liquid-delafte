package cl.uchile.dcc.citric
package model.controllerTests
import munit.FunSuite
import model.controller.GameController

import cl.uchile.dcc.citric.exceptions.InvalidInputException
import cl.uchile.dcc.citric.model.controller.states.{OnPanel, PreGame}
import cl.uchile.dcc.citric.model.norm.{Norm2, Norm6}
import cl.uchile.dcc.citric.model.panels.`trait`.Panel
import cl.uchile.dcc.citric.model.panels.paneltypes.{BonusPanel, DropPanel, EncounterPanel, HomePanel, NeutralPanel}
import cl.uchile.dcc.citric.model.units.players.PlayerCharacter
import cl.uchile.dcc.citric.model.units.traitunits.WildUnit

import scala.collection.mutable.ArrayBuffer
import scala.util.Random
class ControllerTest extends FunSuite {
  private var game: GameController =_
  private val playerCharacters: Seq[(String,Int,Int, Int,Int)] = Seq(("player1", 10,100,2,3),("player2", 10,60,1,1))

  override def beforeEach(context: BeforeEach): Unit = {
    game = new GameController()
  }
  test("game is set with state PreGame"){
    assert(game.state.isInstanceOf[PreGame])
  }
  test("Test change turns"){
    game.startGame(playerCharacters,1)
    val name1:String=game.currentPlayer.name
    assert(game.inChapter())
    game.state = new OnPanel(game)
    game.doAction(1)
    assert(game.currentPlayer.name!=name1)

  }
  test("the players can choose their objective"){
    game.startGame(playerCharacters,1)
    assert(game.currentPlayer.Obj_stars)
    assert(game.playerCharacters(1).Obj_stars)
    assert(!game.currentPlayer.Obj_victories)
    assert(!game.playerCharacters(1).Obj_victories)
  }
  test("the players can choose their objective pt2") {
    game.startGame(playerCharacters, 0)
    assert(game.currentPlayer.Obj_victories)
    assert(game.playerCharacters(1).Obj_victories)
    assert(!game.currentPlayer.Obj_stars)
    assert(!game.playerCharacters(1).Obj_stars)
  }
  test("A player can choose their objective when they make normCheck"){
    game.startGame(playerCharacters, 0)
    assert(game.currentPlayer.Obj_victories)
    game.addNumChapter(2)
    game.doAction(1)
    assert(game.inPlayerTurn())
    game.doAction(1)
    assert(game.inMoving())
    game.doAction(1)
    assert(game.inCombat())
    game.doAction(1)
    game.currentPlayer.addVictories(100)
    assert(game.inOnPanel())
    game.doAction(1)
    assert(!game.playerCharacters.head.Obj_victories)
    assert(game.playerCharacters.head.Obj_stars)
  }
  test("Start of a turn"){
    game.startGame(playerCharacters,1)
    game.doAction(1)
    assert(game.inPlayerTurn())
    game.doAction(1)
    assert(game.rollResult>=1)
  }
  test("Stop decision test when character arrives to their home panel"){
    game.startGame(playerCharacters,1)
    game.addNumChapter(2)
    game.doAction(1)
    assert(game.inPlayerTurn())
    game.doAction(1)
    assert(game.inMoving())
    game.doAction(1)
    assert(game.inCombat())
    game.doAction(1)
    assert(game.inOnPanel())
    assert(game.currentPanel.isInstanceOf[HomePanel])
  }
  test("Stop decision test when character arrives to their home panel pt2") {
    game.startGame(playerCharacters,1)
    game.addNumChapter(2)
    game.doAction(1)
    assert(game.inPlayerTurn())
    game.doAction(1)
    assert(game.inMoving())
    game.doAction(0)
    assert(game.inCombat())
    assert(game.currentPanel!=game.currentPlayer.homePanel)
  }
  test("if a character reaches norm6, the game ends(the observer pattern is well implemented)"){
    game.startGame(playerCharacters,1)
    assert(game.inChapter())
    game.state = new OnPanel(game)
    game.currentPlayer.CurrentNorm = new Norm6()
    assert(game.inEndOfGame())
  }
  test("If a character upgrades norm, they have to choose between stars objective and victories"){
    game.startGame(playerCharacters,1)
    game.addNumChapter(2)
    game.doAction(1)
    assert(game.inPlayerTurn())
    game.doAction(1)
    assert(game.inMoving())
    game.doAction(1)
    assert(game.inCombat())
    game.doAction(1)
    assert(game.inOnPanel())
    game.currentPlayer.addStars(100)
    game.doAction(1)
  }
  test("the recovery process - insufficient roll"){
    game.startGame(playerCharacters,0)
    game.currentPlayer = new PlayerCharacter("player1",10,2,2,2, new Random(11))
    assert(game.inChapter())
    game.currentPlayer.KO=true
    game.doAction(1)
    assert(game.inRecovery())
    game.doAction(1)
    assert(game.inChapter())
  }
  test("the recovery process - sufficient roll") {
    game.startGame(playerCharacters,0)
    assert(game.inChapter())
    game.currentPlayer.KO = true
    game.doAction(1)
    game.addNumChapter(5)
    assert(game.inRecovery())
    game.doAction(1)
    assert(game.inPlayerTurn())
  }

  test("Combat with wildUnit is well implemented"){
    game.startGame(playerCharacters,0)
    game.startTurnPlayer()
    game.addRollResult(1)
    game.rollD()
    game.followingMoves(1)
    game.doAction(1)
    assert(game.inOnPanel())
    game.doAction(1)
    assert(game.inCombat())
    val hpPlayer = game.currentPlayer.CurrentHP
    val hpWild = game.currentEnemy.CurrentHP
    game.doAction(1)
    assert(game.currentEnemy.isInstanceOf[WildUnit])
    assert(game.inWait())
    game.doAction(1)
    assert(game.inCombat())
    game.doAction(1)
    assert(game.inWait())
    game.doAction(1)
    assert(game.inCombat())
    game.doAction(1)
    assert(game.inOnPanel())
    println(s"hp before: player $hpPlayer wild $hpWild")
    assert(game.currentPlayer.CurrentHP<=hpPlayer && game.currentEnemy.CurrentHP < hpWild)

  }
  test("the Combat with other player is well implemented"){
    game.startGame(playerCharacters,1)
    game.startTurnPlayer()
    game.addRollResult(2)
    game.rollD()
    game.followingMoves(1)
    game.doAction(1)
    game.doAction(1)
    assert(game.currentEnemy.CurrentHP<10)
  }
  test("The player can choose if to fight another player"){
    game.startGame(playerCharacters,1)
    game.startTurnPlayer()
    game.addRollResult(2)
    game.rollD()
    game.followingMoves(1)
    game.doAction(0)
    assert(game.inOnPanel())
    game.doAction(0)
    assert(game.inChapter())
  }
  test("A bad input throws exception"){
    game.startGame(playerCharacters,1)
    interceptMessage[InvalidInputException]("An invalid Input was found -- 9 is not an option") {
      game.decideDefendOrEvade(9,game.currentPlayer)
    }
    interceptMessage[InvalidInputException]("An invalid Input was found -- 9 is not an option") {
      game.selectEnemy(9)
    }
    interceptMessage[InvalidInputException]("An invalid Input was found -- 9 is not an option") {
      game.askPathDecision(9)
    }
    interceptMessage[InvalidInputException]("An invalid Input was found -- 9 is not an option") {
      game.askObjectiveNorm(9,game.currentPlayer)
    }
  }

  test("the player can choose if they want to evade or attack"){
    game.startGame(playerCharacters,1)
    game.startTurnPlayer()
    game.addRollResult(2)
    game.rollD()
    game.followingMoves(1)
    assert(game.inCombat())
    game.doAction(1)
    assert(game.inWait())
    game.doAction(0)
    assert(game.inCombat())
  }
  test("if they choose an unexisting option it throws an error"){
    game.startGame(playerCharacters,0)
    game.startTurnPlayer()
    game.addRollResult(2)
    game.rollD()
    game.followingMoves(1)
    interceptMessage[InvalidInputException]("An invalid Input was found -- 9 is not an option") {
      game.doAction(9)
    }
  }

  test("the move-choose first path"){
    assert(game.inPreGame())
    game.startGame(playerCharacters,1)
    assert(game.currentPanel.isInstanceOf[HomePanel])
    game.startTurnPlayer()
    game.addRollResult(3)
    game.rollD()
    game.doAction(1)
    assert(game.currentPanel.isInstanceOf[DropPanel])
  }
  test("the move-choose second path") {
    assert(game.inPreGame())
    game.startGame(playerCharacters,0)
    assert(game.currentPanel.isInstanceOf[HomePanel])
    game.startTurnPlayer()
    game.addRollResult(3)
    game.rollD()
    game.doAction(0)
    assert(game.currentPanel.isInstanceOf[EncounterPanel])

  }
  test("method ask stop decision"){
    game.startGame(playerCharacters,1)
    game.playerRollDice()
    /*if we choose 1, the player stops moving*/
    val roll = game.rollResult
    val result=game.askStopDecision(1)
    assertEquals(game.rollResult,roll)
    assert(result==1)
    /*if we choose 0 the player continues*/
    val result2 = game.askStopDecision(0)
    assert(game.rollResult<roll)
    assert(result2==0)
    /*if we put other number, it throws an exception*/

    interceptMessage[InvalidInputException]("An invalid Input was found -- 9 is not an option") {
      game.askStopDecision(9)
    }
  }

}
