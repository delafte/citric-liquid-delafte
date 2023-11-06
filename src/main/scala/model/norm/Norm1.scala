package cl.uchile.dcc.citric
package model.norm

import model.units.players.PlayerCharacter
/**
 * The class 'Norm1' represents the Norm1 of the game that the players start with.
 * The players have a Norm associated to them that can be upgraded when they fulfill
 * some requirements. Those objectives are reached with a certain quantity of stars or of victories.
 * After completing the requirements for leveling up, the player has to go to a HomePanel, so that they can activate the "Norm Check"
 * and increase their level.
 *
 * @constructor Creates a Norm1.
 *
 * @example {{{
 *   val norma: Norm1 = new Norm1()/*to create a Norm1*/
 *   val stars: Int = norma.Stars_obj
 *   println(s"the stars for this norm are: $stars")
 *   }}}
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
class Norm1() extends AbstractNorm(0,0,1) {
  /** Upgrades the norm of a character if they fulfilled the requirements of the objective that they chose
   *
   * @param character the character that wants to upgrade norm
   * @example {{{
   *   character.Obj_stars = true /*the character chose the stars objective for achieving Norm2*/
   *   character.CurrentStars = 10 /*the character has 10 stars and chose the stars objective before*/
   *   character.CurrentNorm.upgradeNorm(character) /*upgrades the Norm1 to Norm2*/
   * }}} */
  override def upgradeNorm(character: PlayerCharacter): Unit = {
    val norm: Norm2 = new Norm2()
    GeneralUpgrade(norm, character)
  }

}