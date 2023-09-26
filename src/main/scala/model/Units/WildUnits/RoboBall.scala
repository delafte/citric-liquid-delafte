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
class RoboBall(val panel: EncounterPanel) extends Unity{
  /** The RoboBall's capability to deal damage to opponents. It is set as -1 */
  val ATK: Int = -1
  /** The RoboBall's capability to resist or mitigate damage from opponents. It is set as 1 */
  val DEF: Int = 1
  /** The RoboBall's skill to completely avoid certain attacks. It is set as -1 */
  val EVA: Int = -1
  /** The maximum health points a RoboBall can have. It represents the RoboBall's endurance. It is set as 3 */
  val maxHP: Int = 3
  /** The Current Stars that the RoboBall has during the game, it varies during the development of it. It starts as 0. */
  var CurrentStars: Int = 0
  /** The left HP that the RoboBall currently has, it varies during the game. It starts as 3(equal to maxHp) */
  var CurrentHP: Int = 3
}
