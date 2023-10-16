package cl.uchile.dcc.citric
package model.Units.WildUnits

import cl.uchile.dcc.citric.model.Panels.PanelTypesClasses.EncounterPanel
import cl.uchile.dcc.citric.model.Units.WildUnits.AbstractWildUnit
import scala.util.Random

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
 * @param _randomNumberGenerator A utility to generate random numbers. Defaults to a new `Random` instance
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
class RoboBall(private val panel: EncounterPanel, protected val _randomNumberGenerator: Random) extends AbstractWildUnit {
  /** The RoboBall's capability to deal damage to opponents. It is set as -1 */
  protected val _ATK: Int = -1
  /** The RoboBall's capability to resist or mitigate damage from opponents. It is set as 1 */
  protected val _DEF: Int = 1
  /** The RoboBall's skill to completely avoid certain attacks. It is set as -1 */
  protected val _EVA: Int = -1

  /** Returns the RoboBall's encounter panel in which it appears */
  def Panel: EncounterPanel = panel

}
