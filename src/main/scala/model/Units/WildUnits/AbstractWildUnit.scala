package cl.uchile.dcc.citric
package model.Units.WildUnits
import model.Units.AbstractUnity

import cl.uchile.dcc.citric.model.Panels.PanelTypesClasses.EncounterPanel
import model.Units.traitUnits.Unity

import cl.uchile.dcc.citric.model.Units.Players.PlayerCharacter

abstract class AbstractWildUnit extends AbstractUnity{
  /** This function simulates the attack of the WildUnit to other unity
   * @param enemy the enemy of the WildUnit */
  def Attack(enemy: Unity): Unit = {
    if (CurrentHP != 0 && enemy.CurrentHP != 0) {
      /*if the unity isn't out of combat*/
      val result: Int = rollDice()
      val atk: Int = ATK + result
      if (atk < 0) Attack_Quantity = 0 /*There are cases in which an unity can have negative _ATK*/
      else Attack_Quantity = atk
    }
    else Attack_Quantity=0
  }
  /** The maximum health points a WildUnit can have. It represents the WildUnit's endurance. It is set as 3 */
  protected val _maxHP: Int = 3
  /** The current stars of the WildUnit, it starts as 0 */
  protected var _CurrentStars: Int = 0
  /** The attack that the WildUnit is going to apply to their enemy */
  protected var _Attack_Quantity: Int = 0

  /** The current HP of the WildUnit, it starts as the max_HP */
  protected var _CurrentHP: Int = _maxHP
}
