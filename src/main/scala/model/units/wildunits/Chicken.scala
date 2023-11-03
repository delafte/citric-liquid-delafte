package cl.uchile.dcc.citric
package model.units.wildunits
import cl.uchile.dcc.citric.model.panels.paneltypes.EncounterPanel
import cl.uchile.dcc.citric.model.units.players.PlayerCharacter
import cl.uchile.dcc.citric.model.units.traitunits.Unity

import scala.util.Random
/** The 'Chicken' class represents one type of the wild Units that appear aleatory on the board of the game,
 * exclusively, on the Encounter Panels.
 * This wild unit extends from the trait Unity, so that we define the values and variables.
 *
 * Chickens have determined values that differentiates them from the others Wild Units.
 * - ATK = -1
 * - DEF = -1
 * - EVA = 1
 * - maxHP = 3
 *
 * @constructor Creates a Chicken wild unit with a specified Encounter Panel.
 *
 * @param panel An encounter panel in which the chicken is going to appear.
 * @param _randomNumberGenerator A utility to generate random numbers. Defaults to a new `Random` instance
 *
 * @example
 * {{{
 *   val chicken1: Chicken = new Chicken(EncounterPanel1)
 *   val chicken1_attack: Int = chicken1.ATK
 *   println(s"the attack of a Chicken is $chicken1_attack")
 * }}}
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
/*The methods are going to be added later*/
class Chicken(protected val panel: EncounterPanel, override protected val _randomNumberGenerator: Random) extends AbstractWildUnit {
  /** The Chicken's capability to deal damage to opponents. It is set as -1 */
  override protected val _ATK: Int = -1
  /** The Chicken's capability to resist or mitigate damage from opponents. It is set as -1 */
  override protected val _DEF: Int = -1
  /** The Chicken's skill to completely avoid certain attacks. It is set as 1 */
  override protected val _EVA: Int = 1
  /**Bonus stars that the chicken gives when it loses*/
  override protected val _BonusStars = 3
  protected val _EncounterPanel: EncounterPanel= panel


}
