package cl.uchile.dcc.citric
package model.Units.WildUnits
import model.Panels.PanelTypesClasses.EncounterPanel
import model.Units.AbstractUnity
import scala.util.Random
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
 * @param _randomNumberGenerator A utility to generate random numbers. Defaults to a new `Random` instance
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
class Seagull(private val panel: EncounterPanel, val _randomNumberGenerator: Random) extends AbstractUnity {
  /** The Seagull's capability to deal damage to opponents. It is set as 1 */
  protected val _ATK: Int = 1
  /** The Seagull's capability to resist or mitigate damage from opponents. It is set as -1 */
  protected val _DEF: Int = -1
  /** The Seagull's skill to completely avoid certain attacks. It is set as -1 */
  protected val _EVA: Int = -1
  /** The maximum health points a Seagull can have. It represents the Seagull's endurance. It is set as 3 */
  protected val _maxHP: Int = 3
  /** The current stars of the Seagull, it starts as 0 */
  protected var _CurrentStars: Int = 0
  /** The attack that the Seagull is going to apply to their enemy */
  protected var _Attack_Quantity: Int = 0

  /** The current HP of the Seagull, it starts as the max_HP */
  protected var _CurrentHP: Int = _maxHP

  /** Returns the seagull's encounter panel in which it appears */
  def Panel: EncounterPanel = panel
}
