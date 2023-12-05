package cl.uchile.dcc.citric
package model.controllerTests
import munit.FunSuite
import model.controlador.GameController

import cl.uchile.dcc.citric.exceptions.InvalidInputException
import cl.uchile.dcc.citric.model.norm.Norm6
import cl.uchile.dcc.citric.model.panels.`trait`.Panel
import cl.uchile.dcc.citric.model.panels.paneltypes.{BonusPanel, DropPanel, EncounterPanel, HomePanel, NeutralPanel}
import cl.uchile.dcc.citric.model.units.players.PlayerCharacter

import scala.collection.mutable.ArrayBuffer
class ControllerTest extends FunSuite {
  private var game: GameController =_
  private var playerCharacters: Seq[(String,Int,Int, Int,Int)] = Seq(("player1", 10,5,6,3),("player2", 10,6,7,2))

  override def beforeEach(context: BeforeEach): Unit = {
    game = new GameController()
  }
  test("Test of one turn of the game and the change of turns"){
    assert(game.inPreGame())
    game.startGame(playerCharacters)
    assert(game.inChapter())
    assert(game.playerCharacters.length == 2)
    val stars=game.currentPlayer.CurrentStars
    game.doAction(1)
    assert(stars<game.currentPlayer.CurrentStars)
    assert(game.inPlayerTurn())
    game.doAction(1)
    game.addRollResult(1)
    assert(game.inMoving())
    game.doAction(1)
    assert(game.inCombat())
    game.doAction(1)
    assert(game.inOnPanel())
    game.doAction(1)
    assert(game.inChapter())
    game.doAction(1)
    assert(game.inPlayerTurn())
    assert(game.name=="player2")
  }
  test("if a character reaches norm6, the game ends(the observer pattern is well implemented)"){
    game.startGame(playerCharacters)
    assert(game.inChapter())
    game.currentPlayer.CurrentNorm = new Norm6()
    assert(game.inEndOfGame())
  }
  test("the recovery process"){
    game.startGame(playerCharacters)
    assert(game.inChapter())
    game.currentPlayer.KO=true
    game.doAction(1)
    assert(game.inRecovery())
    game.doAction(1)
    assert(game.inChapter() || game.inPlayerTurn())
  }
  test("choose path well implemented"){
    game.startGame(playerCharacters)
    game.StartTurnPlayer()
    game.addRollResult(1)
    game.rollD()
    game.followingMoves(1)
  }
  test("Combat with wildUnit is well implemented"){
    game.startGame(playerCharacters)
    game.StartTurnPlayer()
    game.addRollResult(1)
    game.rollD()
    game.followingMoves(1)
    game.doAction(1)
    assert(game.inOnPanel())
    game.doAction(1)
    assert(game.inCombat())
    game.doAction(1)
    assert(game.inWait())
    game.doAction(1)
    assert(game.inCombat())
    game.doAction(1)
    assert(game.inWait())
    game.doAction(1)
    assert(game.inCombat())
    game.doAction(1)
    assert(game.inOnPanel())

  }
  test("the Combat with other player is well implemented"){
    game.startGame(playerCharacters)
    game.StartTurnPlayer()
    game.addRollResult(2)
    game.rollD()
    game.followingMoves(1)
    game.doAction(1)
    game.doAction(1)

  }
  test("the move"){

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
