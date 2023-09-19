package cl.uchile.dcc.citric
package model.Units.WildUnits
import cl.uchile.dcc.citric.model.Units.traitUnits.Unity
/** The 'Chicken' class represents one type of the wild Units that appears aleatory on the board of the game
 * exclusively, on the Encounter Panels.
 * This wild unit extends from the trait Unity, so that we define the values.
 *
 * Chickens have determined values that differentiates them from the others Wild Units.
 * - ATK = -1
 * - DEF = -1
 * - EVA = 1
 * - maxHP = 3
 *
 * @example
 * {{{
 *   val chicken1: Chicken = new Chicken()
 *   val chicken1_attack: Int = chicken1.ATK
 *   println(s"the attack of a Chicken is $chicken1_attack")
 * }}}
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */


class Chicken extends Unity{
  override val ATK: Int = -1
  override val DEF: Int = -1
  override val EVA: Int = 1
  override val maxHP: Int = 3
  override var CurrentStars: Int = 0
  override var HitPoints: Int = maxHP
}
