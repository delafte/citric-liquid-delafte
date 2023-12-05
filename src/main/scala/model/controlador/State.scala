package cl.uchile.dcc.citric
package model.controlador
/**The State trait declares all the necessary methods for the implementation of the State Pattern. It includes
 * the methods to deliver information of the current State and some other for the transitions between those.
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]*/
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
  def gameStarts(): Unit
  /**This method is for staying in Chapter State*/
  def newChapter(): Unit
  /**This method is for the transition from the chapter state to the End of Game state*/
  def reachNorm6(): Unit
  /**This method is for the transition from the Chapter state to de Player Turn state*/
  def startTurnPlayer():Unit
  /**this method is for the transition from the PlayerTurn State to the Moving state*/
  def rollD():Unit
  /**This method is for the transition form the Recovery state to the Chapter State*/
  def insufficientRoll():Unit
  /**This method is for the transition from the Recovery state to the PlayerTurn state*/
  def sufficientRoll():Unit
  /**this method is for the transition from the Moving state to the OnPanel state*/
  def stopsMoving():Unit
  /**this method is for the transition from the Moving state to the OnPanel state*/
  def outOfMoves():Unit
  /**this method is for the transition from the OnPanel state to the Combat state*/
  def decideNotFightCharacter():Unit
  /**this method is for the transition from the Combat state to the OnPanel state*/
  def endCombat():Unit
  /**this method is for the transition from the Combat state to the wait state*/
  def attacks():Unit
  /**this method is for the transition from the Wait state to the Combat state*/
  def response():Unit

  /**this method is for the transition from the OnPanel state to the Chapter state*/
  def effectApplied():Unit
  /**this method is for the transition from the Moving state to the Moving state*/
  def choosePath():Unit
  /**this method simulates the action that a state does*/
  def doAction(choose:Int):Unit
  /**This method is for the transition from the Chapter state to the Recovery State*/
  def playerKO():Unit
  /**This method is for the transition from the OnPanel state to the Combat state*/
  def fightWildUnit():Unit

}
