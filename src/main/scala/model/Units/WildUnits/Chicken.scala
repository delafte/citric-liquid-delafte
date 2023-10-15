package cl.uchile.dcc.citric
package model.Units.WildUnits
import cl.uchile.dcc.citric.model.Panels.PanelTypesClasses.EncounterPanel
import cl.uchile.dcc.citric.model.Units.AbstractUnity

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
class Chicken(private val panel: EncounterPanel, protected val _randomNumberGenerator: Random) extends AbstractUnity {
  /** The Chicken's capability to deal damage to opponents. It is set as -1 */
  protected val _ATK: Int = -1
  /** The Chicken's capability to resist or mitigate damage from opponents. It is set as -1 */
  protected val _DEF: Int = -1
  /** The Chicken's skill to completely avoid certain attacks. It is set as 1 */
  protected val _EVA: Int = 1
  /** The maximum health points a Chicken can have. It represents the Chicken's endurance. It is set as 3 */
  protected val _maxHP: Int = 3
  /** The current stars of the chicken, it starts as 0 */
  protected var _CurrentStars: Int = 0
  /** The attack that the chicken  is going to apply to their enemy */
  protected var _Attack_Quantity: Int = 0

  /** The current HP of the chicken, it starts as the max_HP */
  protected var _CurrentHP: Int = _maxHP
  def Panel: EncounterPanel = panel
}
