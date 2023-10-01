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
class Chicken(private val panel: EncounterPanel) extends Unity{
  /** The Chicken's capability to deal damage to opponents. It is set as -1*/
  protected val _ATK: Int = -1
  /** The Chicken's capability to resist or mitigate damage from opponents. It is set as -1 */
  protected val _DEF: Int = -1
  /** The Chicken's skill to completely avoid certain attacks. It is set as 1 */
  protected val _EVA: Int = 1
  /** The maximum health points a Chicken can have. It represents the Chicken's endurance. It is set as 3 */
  protected val _maxHP: Int = 3
  /** The Current Stars that the Chicken has during the game, it varies during the development of it. It starts as 0. */
  protected var _CurrentStars: Int = 0
  /** The HP left that the Chicken currently has, it varies during the game. It starts as 3(equal to maxHp)*/
  protected var _CurrentHP: Int = 3

  /**Returns the chicken's max HP*/
  def MaxHP: Int = _maxHP
  /**Returns de chicken's attack*/
  def ATK: Int = _ATK
  /**Returns the chicken's defense*/
  def DEF: Int = _DEF
  /**Returns the chicken's evasion*/
  def EVA: Int = _EVA
  /**Returns the chicken's current HP*/
  def CurrentHP: Int = _CurrentHP
  /**Returns the chicken's current stars*/
  def CurrentStars: Int = _CurrentStars
  /**Returns the chicken's encounter panel in which it appears*/
  def Panel: EncounterPanel = panel
  /**Updates the chicken's current HP value
   * @param HP the new value o the chicken's current HP*/
  def CurrentHP_=(HP: Int): Unit = {
    _CurrentHP = HP
  }
  /**Updates the chicken's amount of stars
   * @param stars the new amount of stars*/
  def CurrentStars_=(stars: Int): Unit = {
    _CurrentStars = stars
  }
}
