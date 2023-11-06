package cl.uchile.dcc.citric
package model.norm

import model.units.players.PlayerCharacter
/**
 * The class 'Norm4' represents the Norm4 of the game that the players have to achieve.
 * The players have a Norm associated to them that can be upgraded when they fulfill
 * some requirements. Those objectives are reached with a certain quantity of stars or of victories,
 * that in the case of Norm4 those are:
 * - 70 stars
 * - 6 victories
 * After completing the requirements for leveling up,the player has to go to a HomePanel, so that they can activate the "Norm Check"
 * and increase their level.
 *
 * @constructor Creates a Norm4.
 *
 * @example
 * {{{
 *   val norma: Norm4 = new Norm4() /*To create a Norm4*/
 *   val stars: Int = norma.Stars_obj
 *   println(s"the stars for this norm are: $stars")
 *   }}}
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
class Norm4() extends AbstractNorm(70,6,4) {

  /** Upgrades the norm of a character if they fulfilled the requirements of the objective that they chose
   *
   * @param character the character that wants to upgrade norm
   * @example {{{
   *   character.Obj_victories = true /*the character chose the stars objective for achieving Norm5*/
   *   character.Victories = 11 /*the character has 11 victories*/
   *   character.CurrentNorm.upgradeNorm(character) /*upgrades the Norm4 to Norm5*/
   * }}} */
  override def upgradeNorm(character: PlayerCharacter): Unit = {
    val norm: Norm5 = new Norm5()
    GeneralUpgrade(norm, character)
  }
}
