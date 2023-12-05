package cl.uchile.dcc.citric
package model.controllerTests
import munit.FunSuite
import model.controlador.GameController

import cl.uchile.dcc.citric.exceptions.InvalidInputException
import cl.uchile.dcc.citric.model.controlador.states.{OnPanel, PreGame}
import cl.uchile.dcc.citric.model.norm.Norm6
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
    game.startGame(playerCharacters)
    assert(game.currentPlayer.name=="player1")
    assert(game.inChapter())
    game.state = new OnPanel(game)
    game.doAction(1)
    assert(game.currentPlayer.name=="player2")

  }
  test("Start of a turn"){
    game.startGame(playerCharacters)
    game.doAction(1)
    assert(game.inPlayerTurn())
    game.doAction(1)
    assert(game.rollResult>=1)
  }
  test("if a character reaches norm6, the game ends(the observer pattern is well implemented)"){
    game.startGame(playerCharacters)
    assert(game.inChapter())
    game.state = new OnPanel(game)
    game.currentPlayer.CurrentNorm = new Norm6()
    assert(game.inEndOfGame())
  }
  test("the recovery process - insufficient roll"){
    game.startGame(playerCharacters)
    game.currentPlayer = new PlayerCharacter("player1",10,2,2,2, new Random(11))
    assert(game.inChapter())
    game.currentPlayer.KO=true
    game.doAction(1)
    assert(game.inRecovery())
    game.doAction(1)
    assert(game.inChapter())
  }
  test("the recovery process - sufficient roll") {
    game.startGame(playerCharacters)
    assert(game.inChapter())
    game.currentPlayer.KO = true
    game.doAction(1)
    game.addNumChapter(5)
    assert(game.inRecovery())
    game.doAction(1)
    assert(game.inPlayerTurn())
  }

  test("Combat with wildUnit is well implemented"){
    game.startGame(playerCharacters)
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
    game.startGame(playerCharacters)
    game.startTurnPlayer()
    game.addRollResult(2)
    game.rollD()
    game.followingMoves(1)
    game.doAction(1)
    game.doAction(1)
    assert(game.currentEnemy.CurrentHP<10)
  }
  test("The player can choose if to fight another player"){
    game.startGame(playerCharacters)
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
    game.startGame(playerCharacters)
    interceptMessage[InvalidInputException]("An invalid Input was found -- 9 is not an option") {
      game.decideDefendOrEvade(9,game.currentPlayer)
    }
    interceptMessage[InvalidInputException]("An invalid Input was found -- 9 is not an option") {
      game.selectEnemy(9)
    }
    interceptMessage[InvalidInputException]("An invalid Input was found -- 9 is not an option") {
      game.askPathDecision(9)
    }
  }

  test("the player can choose if they want to evade or attack"){
    game.startGame(playerCharacters)
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
    game.startGame(playerCharacters)
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
    game.startGame(playerCharacters)
    assert(game.currentPanel.isInstanceOf[HomePanel])
    game.startTurnPlayer()
    game.addRollResult(3)
    game.rollD()
    game.doAction(1)
    assert(game.currentPanel.isInstanceOf[DropPanel])
  }
  test("the move-choose second path") {
    assert(game.inPreGame())
    game.startGame(playerCharacters)
    assert(game.currentPanel.isInstanceOf[HomePanel])
    game.startTurnPlayer()
    game.addRollResult(3)
    game.rollD()
    game.doAction(0)
    assert(game.currentPanel.isInstanceOf[EncounterPanel])

  }
  test("method ask stop decision"){
    game.startGame(playerCharacters)
    game.playerRollDice()
    /*if we choose 1, the player stops moving*/
    val roll = game.rollResult
    val result=game.askStopDecision(1)
    assert(game.rollResult==roll)
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
