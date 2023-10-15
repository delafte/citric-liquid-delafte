package cl.uchile.dcc.citric
package model.Units
import model.Units.traitUnits.Unity
import scala.math._
import scala.util.Random
abstract class AbstractUnity extends Unity {

  /** Rolls a dice and returns a value between 1 to 6.
   */
  def rollDice(): Int = {
    _randomNumberGenerator.nextInt(6) + 1
  }
  /**This function simulates de defense of a unity when it receives an attack
   * @param atk_enemy the attack that the unity is receiving
   */
  def Defense(atk_enemy:Int): Unit = {
    val rollDef: Int= rollDice()
    val df: Int = max(1, atk_enemy-(rollDef+_DEF))
    if (_CurrentHP - df >= 0) _CurrentHP -= df
    else _CurrentHP = 0
  }
  /**This function simulates the attack of the unity to other unity */
  def Attack(): Unit = {
    if(_CurrentHP!=0) {/*if the unity isn't out of combat*/
      val result: Int = rollDice()
      val atk: Int = _ATK + result
      if (result < 0) _Attack_Quantity = 0 /*There are cases in which an unity can have negative _ATK*/
      else _Attack_Quantity = atk
    }
  }
  /**This function simulates de evasion of a unity when it receives an attack
   * @param atk the attack that the unity is receiving
   */
  def Evasion(atk: Int): Unit = {
    val roll: Int = rollDice()
    if(roll+_EVA <= atk){
      if (_CurrentHP - atk >= 0) _CurrentHP -= atk
      else _CurrentHP = 0
    }
  }
  /**Returns the Attack Quantity that the unity is going to use*/
  def Attack_Quantity: Int =_Attack_Quantity
  /**Returns the random number generator of the unity*/
  def randomNumberGenerator: Random = _randomNumberGenerator
  /** Returns the max HP of an Unity */
  def MaxHP: Int = _maxHP

  /** Returns the attack of an Unity */
  def ATK: Int = _ATK

  /** Returns the defense of an Unity */
  def DEF: Int = _DEF

  /** Returns the evasion of an Unity */
  def EVA: Int = _EVA

  /** Returns the current stars of an Unity */
  def CurrentStars: Int = _CurrentStars

  /**The HP left that the Unity currently has, it varies during the game*/
  def CurrentHP: Int = _CurrentHP

  /** updates the current amount of stars of the unity
   *
   * @param Stars the new amount of stars */
  def CurrentStars_=(Stars: Int): Unit = {
    _CurrentStars = Stars
  }

  /** Updates the current HP of the unity.
   *
   * @param newHP The new HP value. */
  def CurrentHP_=(newHP: Int): Unit = {
    _CurrentHP = newHP
  }
}
