package cl.uchile.dcc.citric
package model.controlador
import model.controlador.GameController

import cl.uchile.dcc.citric.exceptions.InvalidActionException
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
  def GameStarts(): Unit = this.error("Chapter")

  /** This method is for staying in Chapter State */
  def NewChapter(): Unit = this.error("Chapter")

  /** This method is for the transition from the chapter state to the End of Game state */
  def ReachNorm6(): Unit = this.error("EndOfGame")

  /** This method is for the transition from the Chapter state to de Player Turn state */
  def StartTurnPlayer(): Unit = this.error("PlayerTurn")

  /** this method is for the transition from the PlayerTurn State to the Moving state */
  def rollD(): Unit = this.error("Moving")

  /** this method is for the transition from the PlayerTurn state to the RecoveryState */
  def PlayerKO(): Unit = this.error("Recovery")

  /** This method is for the transition form the Recovery state to the Chapter State */
  def insufficientRoll(): Unit = this.error("Chapter")

  /** This method is for the transition from the Recovery state to the PlayerTurn state */
  def sufficientRoll(): Unit = this.error("PlayerTurn")

  /** this method is for the transition from the Moving state to the Combat state */
  def stopsMoving(): Unit = this.error("Combat")

  /** this method is for the transition from the Moving state to the Combat state */
  def OutOfMoves(): Unit = this.error("Combat")

  /** this method is for the transition from the Combat state to the OnPanel state */
  def DecideNotFightCharacter(): Unit = this.error("Combat")

  /** this method is for the transition from the Combat state to the OnPanel state */
  def EndCombat(): Unit = this.error("OnPanel")

  /** this method is for the transition from the Combat state to the wait state */
  def Attacks(): Unit = this.error("Wait")

  /** this method is for the transition from the Wait state to the Combat state */
  def response(): Unit = this.error("Combat")

  def fightWildUnit(): Unit = this.error("Combat")

  /** this method is for the transition from the OnPanel state to the Chapter state */
  def applyEffect(): Unit = this.error("Chapter")

  def choosePath(): Unit = this.error("Moving")

  def isKO(): Unit = this.error("Recovery")
}
