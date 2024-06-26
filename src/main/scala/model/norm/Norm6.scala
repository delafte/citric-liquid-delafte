package cl.uchile.dcc.citric
package model.norm

import model.units.players.PlayerCharacter
/**
 * The class 'Norm6' represents the Norm6 of the game that the players have to achieve.
 * The players have a Norm associated to them that can be upgraded when they fulfill
 * some requirements. Those objectives are reached with a certain quantity of stars or of victories,
 * that in the case of Norm6 those are:
 * - 200 stars
 * - 14 victories
 * After completing the requirements for leveling up,the player has to go to a HomePanel, so that they can activate the "Norm Check"
 * and increase their level.
 * In this Norm, the method to upgrade the Norm is empty because this is the last one of the game.
 *
 * @constructor Creates a Norm6.
 *
 * @example
 * {{{
 *   val norma: Norm6 = new Norm6() /*To create a Norm6*/
 *   val stars: Int = norma.Stars_obj
 *   println(s"the stars for this norm are: $stars")
 *   }}}
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
class Norm6() extends AbstractNorm(200,14,6)
