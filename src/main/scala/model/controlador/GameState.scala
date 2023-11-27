package cl.uchile.dcc.citric
package model.controlador
import model.controlador.GameController
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
  private def error(): Unit = throw new AssertionError("Wrong state!")

  /** This method is for the transition from pre-game state to Chapter state */
  def GameStarts(): Unit = this.error()

  /** This method is for staying in Chapter State */
  def NewChapter(): Unit = this.error()

  /** This method is for the transition from the chapter state to the End of Game state */
  def ReachNorm6(): Unit = this.error()

  /** This method is for the transition from the Chapter state to de Player Turn state */
  def StartTurnPlayer(): Unit = this.error()

  /** this method is for the transition from the PlayerTurn State to the Moving state */
  def RollDice_ChoosePath(): Unit = this.error()

  /** this method is for the transition from the PlayerTurn state to the RecoveryState */
  def PlayerKO(): Unit = this.error()

  /** This method is for the transition form the Recovery state to the Chapter State */
  def insufficientRoll(): Unit = this.error()

  /** This method is for the transition from the Recovery state to the PlayerTurn state */
  def sufficientRoll(): Unit = this.error()

  /** this method is for the transition from the Moving state to the OnPanel state */
  def stopsMoving(): Unit = this.error()

  /** this method is for the transition from the Moving state to the OnPanel state */
  def OutOfMoves(): Unit = this.error()

  /** this method is for the transition from the OnPanel state to the Combat state */
  def DecideFightCharacter(): Unit = this.error()

  /** this method is for the transition from thr OnPanel state to the Combat state */
  def OnEncounterPanel(): Unit = this.error()

  /** this method is for the transition from the Combat state to the OnPanel state */
  def EndCombat(): Unit = this.error()

  /** this method is for the transition from the Combat state to the wait state */
  def Attacks(): Unit = this.error()

  /** this method is for the transition from the Wait state to the Combat state */
  def Evades(): Unit = this.error()

  /** this method is for the transition from the Wait state to the Combat state */
  def defends(): Unit = this.error()

  /** this method is for the transition from the OnPanel state to the Chapter state */
  def applyEffect(): Unit = this.error()

  /** this method id for the transition from the OnPanel state to the Chapter state */
  def FinishEncounterPanel(): Unit = this.error()
}
