package cl.uchile.dcc.citric
package model.units.players
import model.units.AbstractUnity

import cl.uchile.dcc.citric.model.norm.{Norm, Norm1}
import model.units.traitunits.Player

import scala.util.Random
/**
 * The 'AbstractPlayerCharacter' abstract class consists on the methods that all types of PlayerCharacters must have.
 * With this implementation we have a better design and an extensible program.
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */

abstract class AbstractPlayerCharacter(protected val _name: String,protected val _maxHP:Int, protected val _ATK: Int, protected val _DEF: Int, protected val _EVA: Int, protected val _randomNumberGenerator: Random) extends AbstractUnity with Player {
  protected var _CurrentHP: Int = _maxHP
  /**Indicates if the character chose the stars objective to upgrade their norm*/
  protected var _Obj_stars: Boolean = false
  /**Indicates if the character chose the victories objective to upgrade their norm*/
  protected var _Obj_victories: Boolean = false
  /**Indicates if the character chose to evade when they receive an attack*/
  protected var _Evade: Boolean = false
  /**Indicates if the character chose to defend when they receive an attack*/
  protected var _Defend: Boolean = false
  /** This variable keeps track on the amount of victories that the character has during the game */
  protected var _Victories: Int = 0
  /** This variable keeps track on the Norm in which the character is in */
  protected var _CurrentNorm: Norm = new Norm1()
  /** The attack that the character is going to apply to their enemy */
  protected var _Attack_Quantity: Int = 0
  /** This variable indicates if a character is in a KO state */
  protected var _KO = false

  /** Returns the name of the character */
  def name: String = _name
  /**This method returns the state of the variable Obj_stars*/
  def Obj_stars: Boolean = _Obj_stars
  /**This method returns the state of the variable Obj_victories*/
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

  /**Return the state of the variable Defend*/
  def Defend: Boolean = _Defend
  /**Return the state of the variable Evade*/
  def Evade: Boolean = _Evade

  /** This function sets a new state to the Defend attribute
   *
   * @param newState the new Boolean of the attribute */
  def Defend_=(newState: Boolean): Unit ={
    _Defend = newState
  }

  /** This function sets a new state to the Evade attribute
   *
   * @param newState the new Boolean of the attribute */

  def Evade_=(newState: Boolean): Unit = {
    _Evade = newState
  }

  /** Returns the KO state of the character */
  def KO: Boolean = _KO
  /** Returns the current norm of the character */
  def CurrentNorm: Norm = _CurrentNorm

  /** Returns the amount of victories of the character */
  def Victories: Int = _Victories

  /** Adds an amount of victories to the Victories of the player
   *
   * @param Vic the amount of victories to add */
  def addVictories(Vic: Int): Unit = {
    if (Vic > 0) _Victories += Vic
  }

  /** removes victories to the Victories of the player
   *
   * @param Vic the amount of victories that are going to be removed */
  def removeVictories(Vic: Int): Unit = {
    if (Vic > 0)
      if (_Victories - Vic >= 0) _Victories -= Vic
      else _Victories = 0

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
  /** This method asks for the input of the user, so that the decision of defend or evade an attack
   * can be implemented. This function is called during the Attack process. It changes the values of the attributes
   * Defend or Evade.*/
  /*This method will be developed later*/
  def DecideDefendOrEvade(): Unit = {}
}
