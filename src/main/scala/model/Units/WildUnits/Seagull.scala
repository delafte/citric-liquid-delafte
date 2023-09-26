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
class Seagull(val panel: EncounterPanel) extends Unity{
  /** The Seagull's capability to deal damage to opponents. It is set as 1 */
  val ATK: Int = 1
  /** The Seagull's capability to resist or mitigate damage from opponents. It is set as -1 */
  val DEF: Int = -1
  /** The Seagull's skill to completely avoid certain attacks. It is set as -1 */
  val EVA: Int = -1
  /** The maximum health points a Seagull can have. It represents the Seagull's endurance. It is set as 3 */
  val maxHP: Int = 3
  /** The Current Stars that the Seagull has during the game, it varies during the development of it. It starts as 0. */
  var CurrentStars: Int = 0
  /** The HP left that the Seagull currently has, it varies during the game. It starts as 3(equal to maxHp) */
  var CurrentHP: Int = 3
}
