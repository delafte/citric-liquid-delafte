package cl.uchile.dcc.citric
package model.Units.WildUnits
import cl.uchile.dcc.citric.model.Panels.PanelTypesClasses.EncounterPanel
import cl.uchile.dcc.citric.model.Units.traitUnits.Unity
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
class Chicken(val panel: EncounterPanel) extends Unity{
  /** The Unity's capability to deal damage to opponents. It is set as -1*/
  val ATK: Int = -1
  /** The Unity's capability to resist or mitigate damage from opponents. It is set as -1 */
  val DEF: Int = -1
  /** The Unity's skill to completely avoid certain attacks. It is set as 1 */
  val EVA: Int = 1
  /** The maximum health points a Unity can have. It represents the Unity's endurance. It is set as 3 */
  val maxHP: Int = 3
  /** The Current Stars that the Unity has during the game, it varies during the development of it. It starts as 0. */
  var CurrentStars: Int = 0
  /** The HP left that the Unity currently has, it varies during the game. It starts as 3(equal to maxHp)*/
  var CurrentHP: Int = 3
}
