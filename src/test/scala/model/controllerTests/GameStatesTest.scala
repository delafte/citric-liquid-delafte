package cl.uchile.dcc.citric
package model.controllerTests
import munit.FunSuite
import model.controlador.GameController

import cl.uchile.dcc.citric.exceptions.InvalidActionException
class GameStatesTest extends FunSuite {
  private var game: GameController = null

  override def beforeEach(context: BeforeEach): Unit = {
    game = new GameController()
  }
  test("test PreGame"){
    assert(game.inPreGame())

    assert(!game.inPlayerTurn())
    assert(!game.inRecovery())
    assert(!game.inChapter())
    assert(!game.inMoving())
    assert(!game.inOnPanel())
    assert(!game.inWait())
    assert(!game.inCombat())
    assert(!game.inEndOfGame())

    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from PreGame to Moving") {
      game.rollD()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from PreGame to PlayerTurn") {
      game.StartTurnPlayer()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from PreGame to Recovery") {
      game.isKO()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from PreGame to Combat") {
      game.stopsMoving()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from PreGame to Wait") {
      game.Attacks()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from PreGame to OnPanel") {
      game.EndCombat()
    }
  }
  test("test GameStarts"){
    game.GameStarts()
    assert(!game.inPreGame())
    assert(game.inChapter())
    assert(!game.inPlayerTurn())
    assert(!game.inRecovery())
    assert(!game.inMoving())
    assert(!game.inOnPanel())
    assert(!game.inWait())
    assert(!game.inCombat())
    assert(!game.inEndOfGame())


    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Chapter to OnPanel") {
      game.EndCombat()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Chapter to Moving") {
      game.rollD()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Chapter to Combat") {
      game.stopsMoving()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Chapter to Wait") {
      game.Attacks()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Chapter to OnPanel") {
      game.EndCombat()
    }
  }
  test("test new Chapter"){
    game.GameStarts()
    game.NewChapter()
    assert(!game.inPreGame())
    assert(!game.inEndOfGame())
    assert(!game.inPlayerTurn())
    assert(game.inChapter())
    assert(!game.inRecovery())
    assert(!game.inMoving())
    assert(!game.inOnPanel())
    assert(!game.inWait())
    assert(!game.inCombat())

    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Chapter to OnPanel") {
      game.EndCombat()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Chapter to Moving") {
      game.rollD()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Chapter to Combat") {
      game.stopsMoving()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Chapter to Wait") {
      game.Attacks()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Chapter to OnPanel") {
      game.EndCombat()
    }
  }
  test("test start turn of player"){
    game.GameStarts()
    game.StartTurnPlayer()
    assert(!game.inEndOfGame())
    assert(!game.inPreGame())
    assert(!game.inChapter())
    assert(game.inPlayerTurn())
    assert(!game.inRecovery())
    assert(!game.inMoving())
    assert(!game.inOnPanel())
    assert(!game.inWait())
    assert(!game.inCombat())

    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from PlayerTurn to OnPanel") {
      game.EndCombat()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from PlayerTurn to Chapter") {
      game.insufficientRoll()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from PlayerTurn to Combat") {
      game.stopsMoving()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from PlayerTurn to Wait") {
      game.Attacks()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from PlayerTurn to Recovery") {
      game.isKO()
    }
  }
  test("test PlayerKO"){
    game.GameStarts()
    game.isKO()
    assert(!game.inChapter())
    assert(!game.inCombat())
    assert(!game.inEndOfGame())
    assert(!game.inMoving())
    assert(!game.inOnPanel())
    assert(!game.inPlayerTurn())
    assert(!game.inPreGame())
    assert(game.inRecovery())
    assert(!game.inWait())

    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Recovery to OnPanel") {
      game.EndCombat()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Recovery to EndOfGame") {
      game.ReachNorm6()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Recovery to Combat") {
      game.stopsMoving()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Recovery to Wait") {
      game.Attacks()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Recovery to Moving") {
      game.rollD()
    }
  }
  test("test insufficient roll"){
    game.GameStarts()
    game.isKO()
    game.insufficientRoll()
    assert(game.inChapter())
    assert(!game.inCombat())
    assert(!game.inEndOfGame())
    assert(!game.inMoving())
    assert(!game.inOnPanel())
    assert(!game.inPlayerTurn())
    assert(!game.inPreGame())
    assert(!game.inRecovery())
    assert(!game.inWait())

    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Chapter to OnPanel") {
      game.EndCombat()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Chapter to Moving") {
      game.rollD()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Chapter to Combat") {
      game.stopsMoving()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Chapter to Wait") {
      game.Attacks()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Chapter to OnPanel") {
      game.EndCombat()
    }
  }
  test("test sufficient roll"){
    game.GameStarts()
    game.isKO()
    game.sufficientRoll()
    assert(!game.inChapter())
    assert(!game.inCombat())
    assert(!game.inEndOfGame())
    assert(!game.inMoving())
    assert(!game.inOnPanel())
    assert(game.inPlayerTurn())
    assert(!game.inPreGame())
    assert(!game.inRecovery())
    assert(!game.inWait())
  }
  test("test rollD"){
    game.GameStarts()
    game.StartTurnPlayer()
    game.rollD()
    assert(!game.inChapter())
    assert(!game.inCombat())
    assert(!game.inEndOfGame())
    assert(game.inMoving())
    assert(!game.inOnPanel())
    assert(!game.inPlayerTurn())
    assert(!game.inPreGame())
    assert(!game.inRecovery())
    assert(!game.inWait())

    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Moving to PlayerTurn") {
      game.sufficientRoll()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Moving to Chapter") {
      game.applyEffect()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Moving to OnPanel") {
      game.EndCombat()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Moving to Wait") {
      game.Attacks()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Moving to Recovery") {
      game.isKO()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Moving to EndOfGame") {
      game.ReachNorm6()
    }
  }
  test("test stop moving"){
    game.GameStarts()
    game.StartTurnPlayer()
    game.rollD()
    game.stopsMoving()
    assert(!game.inChapter())
    assert(game.inCombat())
    assert(!game.inEndOfGame())
    assert(!game.inMoving())
    assert(!game.inOnPanel())
    assert(!game.inPlayerTurn())
    assert(!game.inPreGame())
    assert(!game.inRecovery())
    assert(!game.inWait())

    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Combat to PlayerTurn") {
      game.sufficientRoll()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Combat to Chapter") {
      game.applyEffect()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Combat to Recovery") {
      game.isKO()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Combat to EndOfGame") {
      game.ReachNorm6()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Combat to Chapter") {
      game.NewChapter()
    }

  }
  test("test Out of moves") {
    game.GameStarts()
    game.StartTurnPlayer()
    game.rollD()
    game.OutOfMoves()
    assert(!game.inChapter())
    assert(game.inCombat())
    assert(!game.inEndOfGame())
    assert(!game.inMoving())
    assert(!game.inOnPanel())
    assert(!game.inPlayerTurn())
    assert(!game.inPreGame())
    assert(!game.inRecovery())
    assert(!game.inWait())
  }
  test("test Attacks"){
    game.GameStarts()
    game.StartTurnPlayer()
    game.rollD()
    game.OutOfMoves()
    game.Attacks()
    assert(!game.inChapter())
    assert(!game.inCombat())
    assert(!game.inEndOfGame())
    assert(!game.inMoving())
    assert(!game.inOnPanel())
    assert(!game.inPlayerTurn())
    assert(!game.inPreGame())
    assert(!game.inRecovery())
    assert(game.inWait())
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Wait to OnPanel") {
      game.DecideNotFightCharacter()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Wait to PlayerTurn") {
      game.sufficientRoll()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Wait to Chapter") {
      game.applyEffect()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Wait to Recovery") {
      game.isKO()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Wait to EndOfGame") {
      game.ReachNorm6()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Wait to OnPanel") {
      game.EndCombat()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Wait to Moving") {
      game.rollD()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Wait to Moving") {
      game.choosePath()
    }
  }

  test("test response"){
    game.GameStarts()
    game.StartTurnPlayer()
    game.rollD()
    game.OutOfMoves()
    game.Attacks()
    game.response()
    assert(!game.inChapter())
    assert(game.inCombat())
    assert(!game.inEndOfGame())
    assert(!game.inMoving())
    assert(!game.inOnPanel())
    assert(!game.inPlayerTurn())
    assert(!game.inPreGame())
    assert(!game.inRecovery())
    assert(!game.inWait())
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Combat to Combat") {
      game.fightWildUnit()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Combat to Combat") {
      game.fightWildUnit()
    }
  }

  test("test End of combat"){
    game.GameStarts()
    game.StartTurnPlayer()
    game.rollD()
    game.OutOfMoves()
    game.Attacks()
    game.response()
    game.EndCombat()
    assert(!game.inChapter())
    assert(!game.inCombat())
    assert(!game.inEndOfGame())
    assert(!game.inMoving())
    assert(game.inOnPanel())
    assert(!game.inPlayerTurn())
    assert(!game.inPreGame())
    assert(!game.inRecovery())
    assert(!game.inWait())

    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from OnPanel to Combat") {
      game.OutOfMoves()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from OnPanel to PlayerTurn") {
      game.StartTurnPlayer()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from OnPanel to Recovery") {
      game.isKO()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from OnPanel to EndOfGame") {
      game.ReachNorm6()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from OnPanel to Wait") {
      game.Attacks()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from OnPanel to Moving") {
      game.rollD()
    }
  }
  test("test apply effect"){
    game.startGame(Seq(("player1", 1,1,1,1)))
    game.StartTurnPlayer()
    game.rollD()
    game.OutOfMoves()
    game.DecideNotFightCharacter()
    game.applyEffect()
    assert(game.inChapter())
    assert(!game.inCombat())
    assert(!game.inEndOfGame())
    assert(!game.inMoving())
    assert(!game.inOnPanel())
    assert(!game.inPlayerTurn())
    assert(!game.inPreGame())
    assert(!game.inRecovery())
    assert(!game.inWait())
  }
  test("test EndGame") {
    game.startGame(Seq(("player1", 1,1,1,1)))
    game.StartTurnPlayer()
    game.rollD()
    game.OutOfMoves()
    game.Attacks()
    game.response()
    game.EndCombat()
    game.applyEffect()
    game.ReachNorm6()
    assert(!game.inChapter())
    assert(!game.inCombat())
    assert(game.inEndOfGame())
    assert(!game.inMoving())
    assert(!game.inOnPanel())
    assert(!game.inPlayerTurn())
    assert(!game.inPreGame())
    assert(!game.inRecovery())
    assert(!game.inWait())

    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from EndOfGame to Combat") {
      game.OutOfMoves()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from EndOfGame to PlayerTurn") {
      game.StartTurnPlayer()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from EndOfGame to Recovery") {
      game.isKO()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from EndOfGame to EndOfGame") {
      game.ReachNorm6()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from EndOfGame to Wait") {
      game.Attacks()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from EndOfGame to Moving") {
      game.rollD()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from EndOfGame to Chapter") {
      game.GameStarts()
    }
  }
  test("test DecideNotFightCharacter"){
    game.GameStarts()
    game.StartTurnPlayer()
    game.rollD()
    game.OutOfMoves()
    game.DecideNotFightCharacter()
    assert(!game.inChapter())
    assert(!game.inCombat())
    assert(!game.inEndOfGame())
    assert(!game.inMoving())
    assert(game.inOnPanel())
    assert(!game.inPlayerTurn())
    assert(!game.inPreGame())
    assert(!game.inRecovery())
    assert(!game.inWait())
  }
  test("test fightWildUnit"){
    game.GameStarts()
    game.StartTurnPlayer()
    game.rollD()
    game.OutOfMoves()
    game.DecideNotFightCharacter()
    game.fightWildUnit()
    assert(!game.inChapter())
    assert(game.inCombat())
    assert(!game.inEndOfGame())
    assert(!game.inMoving())
    assert(!game.inOnPanel())
    assert(!game.inPlayerTurn())
    assert(!game.inPreGame())
    assert(!game.inRecovery())
    assert(!game.inWait())
  }
  test("doAction does nothing in EndOfGame and Pregame"){
    game.doAction(1)
    assert(game.inPreGame())
    game.startGame(Seq(("player1", 1, 1, 1, 1)))
    game.StartTurnPlayer()
    game.rollD()
    game.OutOfMoves()
    game.Attacks()
    game.response()
    game.EndCombat()
    game.applyEffect()
    game.ReachNorm6()
    game.doAction(1)
    assert(game.inEndOfGame())

  }

}
