package cl.uchile.dcc.citric
package model.Units.WildUnits

import cl.uchile.dcc.citric.model.Units.traitUnits.Unity

/** The 'RoboBall' class represents one type of the wild units that appears aleatory on the board of the game
 * exclusively, on the Encounter Panels.
 * This wild unit extends from the trait Unity, so that we can define the specific values.
 *
 * RoboBalls have determined values that differentiates them from the others Wild Units.
 * - ATK = -1
 * - DEF = 1
 * - EVA = -1
 * - maxHP = 3
 *
 * @constructor Creates a new RoboBall
 * @example
 * {{{
 *   val robob: RoboBall = new RoboBall()
 *   val robob_attack: Int = rob.ATK
 *   println(s"the attack of a RoboBall is $robob_attack")
 * }}}
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
class RoboBall extends Unity{
  override val ATK: Int = -1
  override val DEF: Int = 1
  override val EVA: Int = -1
  override val maxHP: Int = 3
  override var CurrentStars: Int = 0
  override var HitPoints: Int = maxHP
}
