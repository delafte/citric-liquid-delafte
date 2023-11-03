package cl.uchile.dcc.citric
package model.norm

import model.units.players.PlayerCharacter
/**
 * The class 'Norm5' represents the Norm5 of the game that the players have to achieve.
 * The players have a Norm associated to them that can be upgraded when they fulfill
 * some requirements. Those objectives are reached with a certain quantity of stars or of victories,
 * that in the case of Norm5 those are:
 * - 120 stars
 * - 10 victory
 * After completing the requirements for leveling up,the player has to go to a HomePanel, so that they can activate the "Norm Check"
 * and increase their level.
 *
 * @constructor Creates a Norm5.
 *
 * @example
 * {{{
 *   val norma: Norm5 = new Norm5()
 *   PlayerCharacter.CurrentStars = 120
 *   norma.upgradeNorm(PlayerCharacter) /*upgrades the norm of the character if
 *   the character fulfilled the requirements, in this case, it reaches Norm5*/
 *   }}}
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
class Norm5 extends AbstractNorm {
  /**The amount of stars goals that the players have to reach to upgrade to this norm*/
  protected val _Stars_obj: Int = 120
  /** The amount of victories goals that the players have to reach to upgrade to this norm*/
  protected val _Victories_obj: Int = 10
  /** The number of the Norm */
  protected val _NumberNorm: Int=5

  /** Upgrades the norm of a character if they fulfilled the requirements of the objective that they chose
   *
   * @param character the character that wants to upgrade norm
   * @example {{{
   *   character.Obj_stars = true /*the character chose the stars objective for achieving Norm6*/
   *   character.CurrentStars = 201 /*the character has 201 stars*/
   *   character.CurrentNorm.upgradeNorm(character) /*upgrades the Norm5 to Norm6*/
   * }}}} */
  override def upgradeNorm(character: PlayerCharacter): Unit = {
    if ((character.CurrentStars >= 200 && character.Obj_stars) ||( character.Victories >= 14 && character.Obj_victories)) {
      character.CurrentNorm = new Norm6() /*we use the setter to update the norm of the character*/
      /*and we use the setters of the Objectives to restart them*/
      character.Obj_stars = false
      character.Obj_victories = false
    }
  }

}
