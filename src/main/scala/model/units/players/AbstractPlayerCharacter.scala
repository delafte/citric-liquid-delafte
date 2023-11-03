package cl.uchile.dcc.citric
package model.units.players
import model.units.AbstractUnity
import cl.uchile.dcc.citric.model.norm.{Norm, Norm1}
import model.units.traitunits.Player
/**
 * The 'AbstractPlayerCharacter' abstract class consists on the methods that all types of PlayerCharacters must have.
 * With this implementation we have a better design and an extensible program.
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */

abstract class AbstractPlayerCharacter extends AbstractUnity with Player {
  /**Indicates if the character chose the stars objective to upgrade their norm*/
  protected var _Obj_stars: Boolean = false
  /**Indicates if the character chose the victories objective to upgrade their norm*/
  protected var _Obj_victories: Boolean = false
  /**Indicates if the character chose to defend when they receive an attack*/
  protected var _Evade: Boolean = false
  /**Indicates if the character chose to defend when they receive an attack*/
  override var _Defend: Boolean = false
  /** This variable keeps track on the amount of victories that the character has during the game */
  private var _Victories: Int = 0
  /** This variable keeps track on the Norm in which the character is in */
  private var _CurrentNorm: Norm = new Norm1()
  /** The attack that the character is going to apply to their enemy */
  protected var _Attack_Quantity: Int = 0
  /** This variable indicates if a character is in a KO state */
  protected var _KO = false

  /** Returns the name of the character */
  def name: String = _name
  /**This function returns the state of the variable Obj_stars*/
  def Obj_stars: Boolean = _Obj_stars
  /**This function returns the state of the variable Obj_stars*/
  def Obj_victories:Boolean = _Obj_victories
  /**This function sets a new state to the Obj_stars attribute
   * @param newState the new Boolean of the attribute*/
  def Obj_stars_=(newState: Boolean): Unit ={
    _Obj_stars = newState
  }

  /** This function sets a new state to the Obj_victories attribute
   *
   * @param newState the new Boolean of the attribute */
  def Obj_victories_=(newState: Boolean): Unit = {
    _Obj_victories = newState
  }
  def Defend: Boolean = _Defend
  def Evade: Boolean = _Evade
  def Defend_=(newState: Boolean): Unit ={
    _Defend = newState
  }

  def Evade_=(newState: Boolean): Unit = {
    _Evade = newState
  }

  /** Returns the KO state of the character */
  def KO: Boolean = _KO
  /** Returns the current norm of the character */
  def CurrentNorm: Norm = _CurrentNorm

  /** Returns the amount of victories of the character */
  def Victories: Int = _Victories

  /** Updates the current amount of victories of the character
   *
   * @param newVictories the new amount of victories of the character */
  def Victories_=(newVictories: Int): Unit = {
    _Victories = newVictories
  }

  /** Updates the current Norm of the player.
   *
   * @param newNorm The new Norm of the character. */
  def CurrentNorm_=(newNorm: Norm): Unit = {
    _CurrentNorm = newNorm
  }

  /** Updates the KO state of the player.
   *
   * @param nKO The new state of their KO. */
  def KO_=(nKO: Boolean): Unit = {
    _KO = nKO
  }
  /** This function ask for the input of the user, so that the decision of defend or evade an attack
   * can be implemented. This function is called during the Attack process. It changes the values of the attributes
   * Defend or Evade.*/
  /*This function will be developed later*/
  def DecideDefendOrEvade(): Unit = {}

}
