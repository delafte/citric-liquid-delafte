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
class Norm5() extends AbstractNorm(120,10,5) {

  /** Upgrades the norm of a character if they fulfilled the requirements of the objective that they chose
   *
   * @param character the character that wants to upgrade norm
   * @example {{{
   *   character.Obj_stars = true /*the character chose the stars objective for achieving Norm6*/
   *   character.CurrentStars = 201 /*the character has 201 stars*/
   *   character.CurrentNorm.upgradeNorm(character) /*upgrades the Norm5 to Norm6*/
   * }}}} */
  override def upgradeNorm(character: PlayerCharacter): Unit = {
    val norm: Norm6 = new Norm6()
    GeneralUpgrade(norm, character)
  }
}
