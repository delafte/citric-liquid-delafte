package cl.uchile.dcc.citric
package model.Units
import model.Units.traitUnits.Unity
import scala.math._
import scala.util.Random
abstract class AbstractUnity extends Unity {
  /** Returns the Attack Quantity that the unity is going to use */
  def Attack_Quantity: Int = _Attack_Quantity

  /** Returns the random number generator of the unity */
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

  /** The HP left that the Unity currently has, it varies during the game */
  def CurrentHP: Int = _CurrentHP

  /** updates the current amount of stars of the unity
   *
   * @param Stars the new amount of stars */
  def CurrentStars_=(Stars: Int): Unit = {
    _CurrentStars = Stars
  }

  /** updates the Attack Quantity of the unity
   *
   * @param newATKq the new Attack Quantity */
  def Attack_Quantity_=(newATKq: Int): Unit = {
    _Attack_Quantity = newATKq
  }

  /** Updates the current HP of the unity.
   *
   * @param newHP The new HP value. */
  def CurrentHP_=(newHP: Int): Unit = {
    _CurrentHP = newHP
  }
  /** Rolls a dice and returns a value between 1 to 6.
   */
  def rollDice(): Int = {
    randomNumberGenerator.nextInt(6) + 1
  }
  /**This function simulates de defense of a unity when it receives an attack
   * @param atk_enemy the attack that the unity is receiving
   */
  def Defense(atk_enemy:Int): Unit = {
    val rollDef: Int= rollDice()
    val df: Int = max(1, atk_enemy-(rollDef+_DEF))
    if (CurrentHP - df >= 0) CurrentHP = CurrentHP-df
    else CurrentHP = 0
  }

  /**This function simulates de evasion of a unity when it receives an attack
   * @param atk the attack that the unity is receiving
   */
  def Evasion(atk: Int): Unit = {
    val roll: Int = rollDice()
    if(roll+EVA <= atk){
      if (CurrentHP - atk >= 0) CurrentHP = CurrentHP - atk
      else CurrentHP = 0
    }
  }

}
