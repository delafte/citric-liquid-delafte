package cl.uchile.dcc.citric
package model.units.traitunits

import scala.util.Random
import model.units.players.PlayerCharacter
import model.units.wildunits.{Chicken, RoboBall,Seagull}

/** The 'Unity' Trait represents a unit, which is an entity that will participate
 * in the game. It can be a Character or a Wild Unit.
 *
 * Each Unity has its own parameters depending on its type, which can be determined
 * or is part of the game design.
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
trait Unity {
  /**The Unity's capability to deal damage to opponents.*/
  protected val _ATK: Int

  /**The Unity's capability to resist or mitigate damage from opponents.*/
  protected val _DEF: Int

  /**The Unity's skill to completely avoid certain attacks.*/
  protected val _EVA: Int

  /**The maximum health points a Unity can have. It represents the Unity's endurance.*/
  protected var _maxHP: Int

  /**The Current Stars that the Unity has during the game, it varies during the development of it*/
  protected var _CurrentStars: Int

  /**The HP left that the Unity currently has, it varies during the game*/
  protected var _CurrentHP:Int
  /**The attack that the unity does over an enemy*/
  protected var _Attack_Quantity: Int
  /** An utility to generate random numbers. Defaults to a new `Random` instance*/
  protected val _randomNumberGenerator: Random

  /** Returns the Current Stars of the Unit */
  def CurrentStars: Int
  /** Returns the Attack Quantity that the unity is going to use*/
  def Attack_Quantity:Int
  /**It sets the value of the Unity's parameter CurrentStars
   * @param stars the actualization of the CurrentStars attribute*/
  protected def CurrentStars_=(stars: Int): Unit

  /** It sets the value of the Unity's parameter Attack_Quantity
   *
   * @param atk the actualization of the Attack_Quantity attribute */
  def Attack_Quantity_=(atk: Int): Unit

  /** This method allows a Unity to roll a dice and returns a value between 1 to 6. */
  def rollDice(): Int

  /**This method simulates the attack of the unity to other unity
   * @param enemy the Unity that is going to be attacked*/
  def Attack(enemy: Unity): Unit

  /**This method simulates the process of the attack and the Unity receiving an Attack of the enemy that is a WildUnit
   *
   * @param enemy the WildUnit that attacks*/
  def AttackWildUnit(enemy: WildUnit):Unit

  /** This method simulates the process of the attack and the Unity receiving an Attack of the enemy that is a PlayerCharacter
   *
   * @param enemy the PlayerCharacter that attacks */
  def AttackPlayer(enemy:PlayerCharacter):Unit

  /**This function simulates de defense of a unity when it receives an attack
   * @param atk the attack that the unity receives*/
  def Defense(atk: Int): Unit
  /**This function simulates de evasion of a unity when it receives an attack
   * @param atk the attack that the unity receives*/
  def Evasion(atk: Int): Unit
  /**This method calculates the attack that an unity is going to do */
  protected def GeneralATK(): Unit

  /** adds stars to the CurrentStars of the unity
   *
   * @param stars the stars that are going to be added */
  def addStars(stars: Int): Unit

  /** removes stars to the CurrentStars of the unity
   *
   * @param stars the stars that are going to be removed */
  def removeStars(stars: Int): Unit
  /**getter for the maxHP*/
  def maxHP:Int
  protected def maxHP_=(x: Int): Unit
  /**getter for the current HP*/
  def CurrentHP:Int
  /**setter for the currentHP*/
  protected def CurrentHP_=(newHP: Int): Unit
  /**the response of the unity when it is going to receive an attack*/
  var defendOrEvade: Boolean

}
