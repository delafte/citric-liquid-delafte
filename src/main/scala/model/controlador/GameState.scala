package cl.uchile.dcc.citric
package model.controlador
import model.controlador.GameController

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

  /** This method is for the transition from pre-game state to Chapter state */
  def gameStarts(): Unit = this.error("Chapter")

  /** This method is for staying in Chapter State */
  def newChapter(): Unit = this.error("Chapter")

  /** This method is for the transition from the OnPanel state to the End of Game state */
  def reachNorm6(): Unit = this.error("EndOfGame")

  /** This method is for the transition from the Chapter state to de Player Turn state */
  def startTurnPlayer(): Unit = this.error("PlayerTurn")

  /** this method is for the transition from the PlayerTurn State to the Moving state */
  def rollD(): Unit = this.error("Moving")

  /** This method is for the transition form the Recovery state to the Chapter State */
  def insufficientRoll(): Unit = this.error("Chapter")

  /** This method is for the transition from the Recovery state to the PlayerTurn state */
  def sufficientRoll(): Unit = this.error("PlayerTurn")

  /** this method is for the transition from the Moving state to the Combat state */
  def stopsMoving(): Unit = this.error("Combat")

  /** this method is for the transition from the Moving state to the Combat state */
  def outOfMoves(): Unit = this.error("Combat")

  /** this method is for the transition from the Combat state to the OnPanel state */
  def decideNotFightCharacter(): Unit = this.error("OnPanel")

  /** this method is for the transition from the Combat state to the OnPanel state */
  def endCombat(): Unit = this.error("OnPanel")

  /** this method is for the transition from the Combat state to the wait state */
  def attacks(): Unit = this.error("Wait")

  /** this method is for the transition from the Wait state to the Combat state */
  def response(): Unit = this.error("Combat")

  def fightWildUnit(): Unit = this.error("Combat")

  /** this method is for the transition from the OnPanel state to the Chapter state */
  def effectApplied(): Unit = this.error("Chapter")

  def choosePath(): Unit = this.error("Moving")

  def playerKO(): Unit = this.error("Recovery")
}
