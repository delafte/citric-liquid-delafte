package cl.uchile.dcc.citric
package model.norm

import model.units.players.PlayerCharacter
/**
 * The class 'Norm2' represents the Norm2 of the game that the players have to achieve.
 * The players have a Norm associated to them that can be upgraded when they fulfill
 * some requirements. Those objectives are reached with a certain quantity of stars or of victories,
 * that in the case of Norm2 those are:
 * - 10 stars
 * - 1 victory
 * After completing the requirements for leveling up,the player has to go to a HomePanel, so that they can activate the "Norm Check"
 * and increase their level.
 *
 * @constructor Creates a Norm2.
 *
 * @example
 * {{{
 *   val norma: Norm2 = new Norm2()/*to create a Norm2*/
 *
 *   PlayerCharacter.CurrentStars = 11
 *   norma.upgradeNorm(PlayerCharacter) /*upgrades the norm of the character if
 *   the character fulfilled the requirements, in this case, it levels up to Norm2*/
 *   }}}
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
class Norm2 extends AbstractNorm {
  /**The amount of stars goals that the players have to reach to upgrade to this norm*/
  protected val _Stars_obj: Int = 10
  /** The amount of victories goals that the players have to reach to upgrade to this norm*/
  protected val _Victories_obj: Int = 1
  /** The number of the Norm */
  protected val _NumberNorm: Int = 2

  /** Upgrades the norm of a character if they fulfilled the requirements of the objective that they chose
   *
   * @param character the character that wants to upgrade norm
   * @example {{{
   *   character.Obj_stars = true /*the character chose the stars objective for achieving Norm3*/
   *   character.CurrentStars = 40 /*the character has 40 stars*/
   *   character.CurrentNorm.upgradeNorm(character) /*upgrades the Norm2 to Norm3*/
   * }}}} */
  override def upgradeNorm(character:PlayerCharacter): Unit={
    if ((character.CurrentStars >= 30 && character.Obj_stars) || (character.Victories >= 3 && character.Obj_victories)) {
      character.CurrentNorm = new Norm3() /*we use the setter to update the norm of the character*/
      /*and we use the setters of the Objectives to restart them*/
      character.Obj_stars = false
      character.Obj_victories = false
    }
  }

}