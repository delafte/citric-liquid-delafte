package cl.uchile.dcc.citric
package model.Panels.PanelTypesClasses

import cl.uchile.dcc.citric.model.Panels.AbstractPanel.AbstractPanel

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
  /** This method changes the current stars of the characters that landed on this panel. It sums the minimum
   * result between the multiplication of the rollDice and the character's current norm, and the multiplication
   * between the rollDice and 3.
   *
   * @example
   * {{{
   *   var panel2: BonusPanel = new BonusPanel()
   *   panel2.addCharacter(CharacterAnna)
   *   panel2.GiveStars()
   * }}}
   * */
  def GiveStars():Unit={
    if(characters.nonEmpty) {
      var i: Int = 0
      while(i<characters.length) {
        val roll: Int = characters(i).rollDice()
        characters(i).CurrentStars += min(roll * characters(i).CurrentNorm, roll * 3)
        i += 1
      }
    }
  }
}
