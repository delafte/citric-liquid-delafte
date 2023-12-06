package cl.uchile.dcc.citric
package model.controller
import model.controller.GameController

import cl.uchile.dcc.citric.exceptions.InvalidActionException
/**The GameState class is part of the implementation of the State Pattern. With this we can pre-establish the
 * behaviour of the methods. We ensure a good implementation of the transitions and general behaviour of the game
 * with the created exceptions.
 *
 * @param context the GameController of the game
 * @author [[https://github.com/delafte/ Delaney Tello E.]]*/
class GameState protected(var context : GameController) extends State{
  def inPreGame() = false
  def inChapter() = false
  def inEndOfGame() = false
  def inPlayerTurn() = false
  def inMoving() = false
  def inRecovery()=false
  def inOnPanel()=false
  def inCombat()=false
  def inWait()=false
  def setState(aState: State): Unit = context.setState(aState)

  context.setState(this)

  def setGameController(gameController: GameController): Unit = {
    context = gameController
  }
  def doAction(choose:Int):Unit={}
  private def error(targetState: String): Unit ={
    throw new InvalidActionException(s"Cannot transition from ${getClass.getSimpleName} to $targetState")
  }

  def gameStarts(): Unit = this.error("Chapter")

  def newChapter(): Unit = this.error("Chapter")

  def reachNorm6(): Unit = this.error("EndOfGame")

  def startTurnPlayer(): Unit = this.error("PlayerTurn")

  def rollD(): Unit = this.error("Moving")

  def insufficientRoll(): Unit = this.error("Chapter")

  def sufficientRoll(): Unit = this.error("PlayerTurn")

  def stopsMoving(): Unit = this.error("Combat")

  def outOfMoves(): Unit = this.error("Combat")

  def decideNotFightCharacter(): Unit = this.error("OnPanel")

  def endCombat(): Unit = this.error("OnPanel")

  def attacks(): Unit = this.error("Wait")

  def response(): Unit = this.error("Combat")

  def fightWildUnit(): Unit = this.error("Combat")

  def effectApplied(): Unit = this.error("Chapter")

  def choosePath(): Unit = this.error("Moving")

  def playerKO(): Unit = this.error("Recovery")
}
