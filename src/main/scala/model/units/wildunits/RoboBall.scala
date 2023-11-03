package cl.uchile.dcc.citric
package model.units.wildunits

import cl.uchile.dcc.citric.model.panels.paneltypes.EncounterPanel
import cl.uchile.dcc.citric.model.units.players.PlayerCharacter
import cl.uchile.dcc.citric.model.units.traitunits.Unity

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
class RoboBall(protected val panel: EncounterPanel, override protected val _randomNumberGenerator: Random) extends AbstractWildUnit {
  /** The RoboBall's capability to deal damage to opponents. It is set as -1 */
  override protected val _ATK: Int = -1
  /** The RoboBall's capability to resist or mitigate damage from opponents. It is set as 1 */
  override protected val _DEF: Int = 1
  /** The RoboBall's skill to completely avoid certain attacks. It is set as -1 */
  override protected val _EVA: Int = -1
  /** Bonus stars that the RoboBall gives when it loses */
  override protected val _BonusStars = 2
  protected val _EncounterPanel: EncounterPanel= panel

}
