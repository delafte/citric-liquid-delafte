package cl.uchile.dcc.citric
package model.Units.WildUnits
import cl.uchile.dcc.citric.model.Panels.PanelTypesClasses.EncounterPanel
import cl.uchile.dcc.citric.model.Units.traitUnits.Unity
/** The 'Seagull' class represents one type of the wild units that appears aleatory on the board of the game,
 * exclusively, on the Encounter Panels.
 * This wild unit extends from the trait Unity, so that we define the specific values and variables.
 *
 * Seagulls have determined values that differentiates them from the others Wild Units.
 * - ATK = 1
 * - DEF = -1
 * - EVA = -1
 * - maxHP = 3
 *
 * @constructor Creates a Seagull wild unit with a specified Encounter panel.
 *
 * @param panel An Encounter Panel in which the Seagull is going to appear
 *
 * @example
 * {{{
 *   val seagull: Seagull = new Seagull()
 *   val maximumHp: Int = seagull.maxHP
 *   println(s"the maximum HP of a seagull is $maximumHp")
 * }}}
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
/*The methods are going to be added later*/
class Seagull(private val panel: EncounterPanel) extends Unity{
  /** The Seagull's capability to deal damage to opponents. It is set as 1 */
  protected val _ATK: Int = 1
  /** The Seagull's capability to resist or mitigate damage from opponents. It is set as -1 */
  protected val _DEF: Int = -1
  /** The Seagull's skill to completely avoid certain attacks. It is set as -1 */
  protected val _EVA: Int = -1
  /** The maximum health points a Seagull can have. It represents the Seagull's endurance. It is set as 3 */
  protected val _maxHP: Int = 3
  /** The Current Stars that the Seagull has during the game, it varies during the development of it. It starts as 0. */
  protected var _CurrentStars: Int = 0
  /** The HP left that the Seagull currently has, it varies during the game. It starts as 3(equal to maxHp) */
  protected var _CurrentHP: Int = 3

  /** Returns the seagull's max HP */
  def MaxHP: Int = _maxHP

  /** Returns de seagull's attack */
  def ATK: Int = _ATK

  /** Returns the seagull's defense */
  def DEF: Int = _DEF

  /** Returns the seagull's evasion */
  def EVA: Int = _EVA

  /** Returns the seagull's current HP */
  def CurrentHP: Int = _CurrentHP

  /** Returns the seagull's current stars */
  def CurrentStars: Int = _CurrentStars

  /** Returns the seagull's encounter panel in which it appears */
  def Panel: EncounterPanel = panel

  /** Updates the seagull's current HP value
   *
   * @param HP the new value o the seagull's current HP */
  def CurrentHP_=(HP: Int): Unit = {
    _CurrentHP = HP
  }

  /** Updates the seagull's amount of stars
   *
   * @param stars the new amount of stars */
  def CurrentStars_=(stars: Int): Unit = {
    _CurrentStars = stars
  }
}
