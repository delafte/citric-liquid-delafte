package cl.uchile.dcc.citric
package model.units.wildunits
import model.units.AbstractUnity
import model.units.players.PlayerCharacter
import model.units.traitunits.{Unity, WildUnit}

import exceptions.InvalidAttackException
import model.panels.paneltypes.EncounterPanel

/**
 * The 'AbstractWildUnit' abstract class consists on the methods that all types of WildUnits must have.
 * With this implementation we avoid the repetition of code.
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
abstract class AbstractWildUnit(protected var _maxHP: Int,protected val _ATK: Int, protected val _DEF: Int, protected val _EVA: Int, protected val _BonusStars: Int) extends AbstractUnity with WildUnit{
  /**Recovers the current Hp of the wildUnit when it reaches 0*/
  def recover():Unit={
    if(CurrentHP ==0)CurrentHP = maxHP
   }
  /** This method simulates the attack of the WildUnit to other unity
   *
   * @param enemy the Unity that is going to be attacked
   * @throws InvalidAttackException when enemy is a WildUnit*/
  def Attack(enemy: Unity): Unit = {
    if(CurrentHP>0){
      GeneralATK()
    }
    enemy.AttackWildUnit(this)
  }

  /** This method throws an exception for the fight between WildUnits and resets the Attack_Quantity of the attacker
   *
   * @param enemy the WildUnit that attacks
   * @throws InvalidAttackException always*/
  def AttackWildUnit(enemy: WildUnit): Unit = {
    enemy.Attack_Quantity = 0
    throw new InvalidAttackException()
  }
  def AttackPlayer(enemy: PlayerCharacter): Unit = {
    if(CurrentHP > 0) {
      val i = rollDice()/*to obtain a random number*/
      if (i <= 3) Defense(enemy.Attack_Quantity)
      else Evasion(enemy.Attack_Quantity)
      if (CurrentHP == 0) {
        enemy.addStars(CurrentStars + BonusStars)
        CurrentStars = 0
        enemy.addVictories( 1)
      }
    }
    else enemy.Attack_Quantity = 0
  }
  /**Gives the Amount of Bonus Stars of the wild unit*/
  def BonusStars: Int = _BonusStars
  /** The attack that the WildUnit is going to apply to their enemy, it starts as 0 */
  protected var _Attack_Quantity: Int = 0

  /** The current HP of the WildUnit, it starts as the maxHP */
  protected var _CurrentHP: Int = _maxHP
  /**The EncounterPanel in which the WildUnit will appear*/
  def EncounterPanel: EncounterPanel = _EncounterPanel
}
