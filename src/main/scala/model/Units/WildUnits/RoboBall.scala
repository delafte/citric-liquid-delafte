package cl.uchile.dcc.citric
package model.Units.WildUnits

import cl.uchile.dcc.citric.model.Panels.PanelTypesClasses.EncounterPanel
import cl.uchile.dcc.citric.model.Units.traitUnits.Unity

/** The 'RoboBall' class represents one type of the wild units that appears aleatory on the board of the game
 * exclusively, on the Encounter Panels.
 * This wild unit extends from the trait Unity, so that we can define the specific values and variables.
 *
 * RoboBalls have determined values that differentiates them from the others Wild Units.
 * - ATK = -1
 * - DEF = 1
 * - EVA = -1
 * - maxHP = 3
 *
 * @constructor Creates a RoboBall wild unit with a specified encounter panel.
 *
 * @param panel An Encounter Panel in which the RoboBall is going to appear.
 *
 * @example
 * {{{
 *   val robob: RoboBall = new RoboBall(EncounterPanel1)
 *   val robob_attack: Int = rob.ATK
 *   println(s"the attack of a RoboBall is $robob_attack")
 * }}}
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
/*The methods are going to be added later*/
class RoboBall(private val panel: EncounterPanel) extends Unity{
  /** The RoboBall's capability to deal damage to opponents. It is set as -1 */
  protected val _ATK: Int = -1
  /** The RoboBall's capability to resist or mitigate damage from opponents. It is set as 1 */
  protected val _DEF: Int = 1
  /** The RoboBall's skill to completely avoid certain attacks. It is set as -1 */
  protected val _EVA: Int = -1
  /** The maximum health points a RoboBall can have. It represents the RoboBall's endurance. It is set as 3 */
  protected val _maxHP: Int = 3
  /** The Current Stars that the RoboBall has during the game, it varies during the development of it. It starts as 0. */
  protected var _CurrentStars: Int = 0
  /** The left HP that the RoboBall currently has, it varies during the game. It starts as 3(equal to maxHp) */
  protected var _CurrentHP: Int = 3

  /** Returns the RoboBall's max HP */
  def MaxHP: Int = _maxHP

  /** Returns de RoboBall's attack */
  def ATK: Int = _ATK

  /** Returns the RoboBall's defense */
  def DEF: Int = _DEF

  /** Returns the RoboBall's evasion */
  def EVA: Int = _EVA

  /** Returns the RoboBall's current HP */
  def CurrentHP: Int = _CurrentHP

  /** Returns the RoboBall's current stars */
  def CurrentStars: Int = _CurrentStars

  /** Returns the RoboBall's encounter panel in which it appears */
  def Panel: EncounterPanel = panel

  /** Updates the RoboBall's current HP value
   *
   * @param HP the new value o the RoboBall's current HP */
  def CurrentHP_=(HP: Int): Unit = {
    _CurrentHP = HP
  }

  /** Updates the RoboBall's amount of stars
   *
   * @param stars the new amount of stars */
  def CurrentStars_=(stars: Int): Unit = {
    _CurrentStars = stars
  }
}
