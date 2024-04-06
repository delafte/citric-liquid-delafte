package cl.uchile.dcc.citric
package model.statesTransitionsTest

import exceptions.InvalidActionException
import model.controller.GameController

import munit.FunSuite
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
      game.startTurnPlayer()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from PreGame to Recovery") {
      game.playerKO()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from PreGame to Combat") {
      game.stopsMoving()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from PreGame to Wait") {
      game.attacks()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from PreGame to OnPanel") {
      game.endCombat()
    }
  }
  test("test GameStarts"){
    game.gameStarts()
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
      game.endCombat()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Chapter to Moving") {
      game.rollD()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Chapter to Combat") {
      game.stopsMoving()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Chapter to Wait") {
      game.attacks()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Chapter to OnPanel") {
      game.endCombat()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Chapter to EndOfGame") {
      game.reachNorm6()
    }
  }
  test("test new Chapter"){
    game.gameStarts()
    game.newChapter()
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
      game.endCombat()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Chapter to Moving") {
      game.rollD()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Chapter to Combat") {
      game.stopsMoving()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Chapter to Wait") {
      game.attacks()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Chapter to OnPanel") {
      game.endCombat()
    }
  }
  test("test start turn of player"){
    game.gameStarts()
    game.startTurnPlayer()
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
      game.endCombat()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from PlayerTurn to Chapter") {
      game.insufficientRoll()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from PlayerTurn to Combat") {
      game.stopsMoving()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from PlayerTurn to Wait") {
      game.attacks()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from PlayerTurn to Recovery") {
      game.playerKO()
    }
  }
  test("test PlayerKO"){
    game.gameStarts()
    game.playerKO()
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
      game.endCombat()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Recovery to EndOfGame") {
      game.reachNorm6()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Recovery to Combat") {
      game.stopsMoving()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Recovery to Wait") {
      game.attacks()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Recovery to Moving") {
      game.rollD()
    }
  }
  test("test insufficient roll"){
    game.gameStarts()
    game.playerKO()
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
      game.endCombat()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Chapter to Moving") {
      game.rollD()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Chapter to Combat") {
      game.stopsMoving()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Chapter to Wait") {
      game.attacks()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Chapter to OnPanel") {
      game.endCombat()
    }
  }
  test("test sufficient roll"){
    game.gameStarts()
    game.playerKO()
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
    game.gameStarts()
    game.startTurnPlayer()
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
      game.effectApplied()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Moving to OnPanel") {
      game.endCombat()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Moving to Wait") {
      game.attacks()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Moving to Recovery") {
      game.playerKO()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Moving to EndOfGame") {
      game.reachNorm6()
    }
  }
  test("test stop moving"){
    game.gameStarts()
    game.startTurnPlayer()
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
      game.effectApplied()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Combat to Recovery") {
      game.playerKO()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Combat to EndOfGame") {
      game.reachNorm6()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Combat to Chapter") {
      game.newChapter()
    }

  }
  test("test Out of moves") {
    game.gameStarts()
    game.startTurnPlayer()
    game.rollD()
    game.outOfMoves()
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
    game.gameStarts()
    game.startTurnPlayer()
    game.rollD()
    game.outOfMoves()
    game.attacks()
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
      game.decideNotFightCharacter()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Wait to PlayerTurn") {
      game.sufficientRoll()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Wait to Chapter") {
      game.effectApplied()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Wait to Recovery") {
      game.playerKO()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Wait to EndOfGame") {
      game.reachNorm6()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Wait to OnPanel") {
      game.endCombat()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Wait to Moving") {
      game.rollD()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from Wait to Moving") {
      game.choosePath()
    }
  }

  test("test response"){
    game.gameStarts()
    game.startTurnPlayer()
    game.rollD()
    game.outOfMoves()
    game.attacks()
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
    game.gameStarts()
    game.startTurnPlayer()
    game.rollD()
    game.outOfMoves()
    game.attacks()
    game.response()
    game.endCombat()
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
      game.outOfMoves()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from OnPanel to PlayerTurn") {
      game.startTurnPlayer()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from OnPanel to Recovery") {
      game.playerKO()
    }

    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from OnPanel to Wait") {
      game.attacks()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from OnPanel to Moving") {
      game.rollD()
    }
  }
  test("test apply effect"){
    game.startGame(Seq(("player1", 1,1,1,1)),1)
    game.startTurnPlayer()
    game.rollD()
    game.outOfMoves()
    game.decideNotFightCharacter()
    game.effectApplied()
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
    game.startGame(Seq(("player1", 1,1,1,1)),0)
    game.startTurnPlayer()
    game.rollD()
    game.outOfMoves()
    game.attacks()
    game.response()
    game.endCombat()
    game.reachNorm6()
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
      game.outOfMoves()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from EndOfGame to PlayerTurn") {
      game.startTurnPlayer()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from EndOfGame to Recovery") {
      game.playerKO()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from EndOfGame to EndOfGame") {
      game.reachNorm6()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from EndOfGame to Wait") {
      game.attacks()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from EndOfGame to Moving") {
      game.rollD()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from EndOfGame to Chapter") {
      game.gameStarts()
    }
    interceptMessage[InvalidActionException]("An invalid Action was found -- Cannot transition from EndOfGame to Combat") {
      game.response()
    }
  }
  test("test DecideNotFightCharacter"){
    game.gameStarts()
    game.startTurnPlayer()
    game.rollD()
    game.outOfMoves()
    game.decideNotFightCharacter()
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
    game.gameStarts()
    game.startTurnPlayer()
    game.rollD()
    game.outOfMoves()
    game.decideNotFightCharacter()
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
    game.startGame(Seq(("player1", 1, 1, 1, 1)),1)
    game.startTurnPlayer()
    game.rollD()
    game.outOfMoves()
    game.attacks()
    game.response()
    game.endCombat()
    game.reachNorm6()
    game.doAction(1)
    assert(game.inEndOfGame())

  }

}
