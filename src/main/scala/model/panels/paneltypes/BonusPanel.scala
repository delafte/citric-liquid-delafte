package cl.uchile.dcc.citric
package model.panels.paneltypes

import model.panels.abstractpanel.AbstractPanel

import cl.uchile.dcc.citric.model.controller.GameController
import cl.uchile.dcc.citric.model.units.players.PlayerCharacter

import scala.math._
/**
 * The 'BonusPanel' class represents a panel on the game's board that gives all the characters that
 * land on it, a bonus of stars. When the players land, they have to roll the dice, and depending on the
 * numerical result, they will obtain certain amount of stars.This Panel extends from the abstract class AbstractPanel.
 *
 * @constructor Creates a BonusPanel.
 *
 * @example
 * {{{
 *   var panel1: BonusPanel = new BonusPanel()
 *   panel1.addCharacter(CharacterEmma)
 *   panel1.GiveStars()
 * }}}
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 * */
class BonusPanel () extends AbstractPanel {
  val name = "Bonus Panel"
  /** This method changes the current stars of the characters that landed on this panel. It sums the minimum
   * result between the multiplication of the rollDice and the character's current norm, and the multiplication
   * between the rollDice and 3.
   *
   * @example
   * {{{
   *   var panel2: BonusPanel = new BonusPanel()
   *   panel2.addCharacter(CharacterAnna)
   *   panel2.apply(CharacterAnna)
   * }}}
   * */
  override def apply(player:PlayerCharacter, context: GameController):Unit={
    if(characters.contains(player)) {
        val roll: Int = player.rollDice()
        player.addStars(min(roll * player.CurrentNorm.NumberNorm, roll * 3))
    }
  }
}
