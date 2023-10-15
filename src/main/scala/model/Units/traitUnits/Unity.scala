package cl.uchile.dcc.citric
package model.Units.traitUnits

import scala.util.Random

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
  protected val _maxHP: Int

  /**The Current Stars that the Unity has during the game, it varies during the development of it*/
  protected var _CurrentStars: Int

  /**The HP left that the Unity currently has, it varies during the game*/
  protected var _CurrentHP:Int
  /**The attack that the unity does over an enemy*/
  protected var _Attack_Quantity: Int
  /** A utility to generate random numbers. Defaults to a new `Random` instance*/
  protected val _randomNumberGenerator: Random
  /**This function simulates the attack of the unity to other unity*/
  def Attack(): Unit
  /**This function simulates de defense of a unity when it receives an attack*/
  def Defense(atk: Int): Unit
  /**This function simulates de evasion of a unity when it receives an attack*/
  def Evasion(atk: Int): Unit
}
