package cl.uchile.dcc.citric
package model.norm

import model.units.players.PlayerCharacter
/**
 * The class 'Norm3' represents the Norm3 of the game that the players have to achieve.
 * The players have a Norm associated to them that can be upgraded when they fulfill
 * some requirements. Those objectives are reached with a certain quantity of stars or of victories,
 * that in the case of Norm3 those are:
 * - 30 stars
 * - 3 victories
 * After completing the requirements for leveling up,the player has to go to a HomePanel, so that they can activate the "Norm Check"
 * and increase their level.
 *
 * @constructor Creates a Norm3.
 *
 * @example
 * {{{
 *   val norma: Norm3 = new Norm3()/*to create a Norm3*/
 *   val stars: Int = norma.Stars_obj
 *   println(s"the stars for this norm are: $stars")
 *   }}}
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
class Norm3() extends AbstractNorm(30,3,3) {

  /** Upgrades the norm of a character if they fulfilled the requirements of the objective that they chose
   *
   * @param character the character that wants to upgrade norm
   * @example {{{
   *   character.Obj_victories = true /*the character chose the stars objective for achieving Norm4*/
   *   character.Victories = 7 /*the character has 7 victories*/
   *   character.CurrentNorm.upgradeNorm(character) /*upgrades the Norm3 to Norm4*/
   * }}} */
  override def upgradeNorm( character: PlayerCharacter): Unit = {
    val norm: Norm4 = new Norm4()
    GeneralUpgrade(norm, character)
  }
}
