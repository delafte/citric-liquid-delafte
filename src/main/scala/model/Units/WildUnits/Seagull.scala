package cl.uchile.dcc.citric
package model.Units.WildUnits
import cl.uchile.dcc.citric.model.Units.traitUnits.Unity
/** The 'Seagull' class represents one type of the wild units that appear aleatory on the board of the game,
 * exclusively, on the Encounter Panels.
 * This wild unit extends from the trait Unity, so that we define the specific values.
 *
 * Seagulls have determined values that differentiates them from the others Wild Units.
 * - ATK = 1
 * - DEF = -1
 * - EVA = -1
 * - maxHP = 3
 *
 * @constructor Creates a new Seagull
 * @example
 * {{{
 *   val seagull: Seagull = new Seagull()
 *   val maximumHp: Int = seagull.maxHP
 *   println(s"the maximum HP of a seagull is $maximumHp")
 * }}}
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */

class Seagull extends Unity{
  override val ATK: Int = 1
  override val DEF: Int = -1
  override val EVA: Int = -1
  override val maxHP: Int = 3
  override var CurrentStars: Int = 0
  override var HitPoints: Int = maxHP
}
