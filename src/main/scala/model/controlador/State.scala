package cl.uchile.dcc.citric
package model.controlador

trait State {
  /**Indicates if the Current State is PreGame or not*/
  def inPreGame():Boolean

  /** Indicates if the Current State is Chapter or not */
  def inChapter(): Boolean

  /** Indicates if the Current State is EndOfGame or not */
  def inEndOfGame(): Boolean

  /** Indicates if the Current State is PlayerTurn or not */
  def inPlayerTurn(): Boolean

  /** Indicates if the Current State is Combat or not */
  def inCombat(): Boolean

  /** Indicates if the Current State is Moving or not */
  def inMoving(): Boolean

  /** Indicates if the Current State is OnPanel or not */
  def inOnPanel(): Boolean

  /** Indicates if the Current State is Recovery or not */
  def inRecovery(): Boolean

  /** Indicates if the Current State is Wait or not */
  def inWait(): Boolean
  /**sets the GameController
   * @param a the game controller we want*/
  def setGameController(a:GameController): Unit
  /**Sets the state of a Current State
   * @param a the state we want*/
  def setState(a:State): Unit
  /**This method is for the transition from pre-game state to Chapter state*/
  def GameStarts(): Unit
  /**This method is for staying in Chapter State*/
  def NewChapter(): Unit
  /**This method is for the transition from the chapter state to the End of Game state*/
  def ReachNorm6(): Unit
  /**This method is for the transition from the Chapter state to de Player Turn state*/
  def StartTurnPlayer():Unit
  /**this method is for the transition from the PlayerTurn State to the Moving state*/
  def rollD():Unit
  /**This method is for the transition form the Recovery state to the Chapter State*/
  def insufficientRoll():Unit
  /**This method is for the transition from the Recovery state to the PlayerTurn state*/
  def sufficientRoll():Unit
  /**this method is for the transition from the Moving state to the OnPanel state*/
  def stopsMoving():Unit
  /**this method is for the transition from the Moving state to the OnPanel state*/
  def OutOfMoves():Unit
  /**this method is for the transition from the OnPanel state to the Combat state*/
  def DecideNotFightCharacter():Unit
  /**this method is for the transition from the Combat state to the OnPanel state*/
  def EndCombat():Unit
  /**this method is for the transition from the Combat state to the wait state*/
  def Attacks():Unit
  /**this method is for the transition from the Wait state to the Combat state*/
  def response():Unit

  /**this method is for the transition from the OnPanel state to the Chapter state*/
  def applyEffect():Unit
  /**this method is for the transition from the Moving state to the Moving state*/
  def choosePath():Unit
  /**this method simulates the action that a state does*/
  def doAction(choose:Int):Unit
  /**This method is for the transition from the Chapter state to the Recovery State*/
  def isKO():Unit
  /**This method is for the transition from the OnPanel state to the Combat state*/
  def fightWildUnit():Unit

}
