package cl.uchile.dcc.citric
package model.units.wildunits
import model.units.AbstractUnity
import model.units.players.PlayerCharacter
import model.units.traitunits.{Unity, WildUnit}

import cl.uchile.dcc.citric.model.panels.paneltypes.EncounterPanel
/**
 * The 'AbstractWildUnit' abstract class consists on the methods that all types of WildUnits must have.
 * With this implementation we avoid the repetition of code.
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
abstract class AbstractWildUnit extends AbstractUnity with WildUnit{
  def Attack(enemy: Unity): Unit = {
    if(CurrentHP>0){
      GeneralATK()
    }
    enemy.AttackWildUnit(this)
  }
  override def AttackWildUnit(enemy: WildUnit): Unit = {
    enemy.Attack_Quantity = 0
  }
  def AttackPlayer(enemy: PlayerCharacter): Unit = {
    if(CurrentHP > 0) {
      val i = rollDice()
      if (i <= 3) Defense(enemy.Attack_Quantity)
      else Evasion(enemy.Attack_Quantity)
      if (CurrentHP == 0) {
        enemy.CurrentStars += CurrentStars + BonusStars
        CurrentStars = 0
        enemy.Victories += 1
      }
    }
    else enemy.Attack_Quantity = 0
  }
  /**Bonus stars that the WildUnit gives when it loses*/
  protected val _BonusStars: Int
  /**Gives the Amount of Bonus Stars of the wild unit*/
  def BonusStars: Int = _BonusStars
  /** The maximum health points a WildUnit can have. It represents the WildUnit's endurance. It is set as 3 */
  protected val _maxHP: Int = 3
  /** The attack that the WildUnit is going to apply to their enemy */
  protected var _Attack_Quantity: Int = 0

  /** The current HP of the WildUnit, it starts as the max_HP */
  protected var _CurrentHP: Int = _maxHP
  /**The EncounterPanel in which the WildUnit will appear*/
  def EncounterPanel: EncounterPanel = _EncounterPanel
}
