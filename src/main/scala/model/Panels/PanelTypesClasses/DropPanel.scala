package cl.uchile.dcc.citric
package model.Panels.PanelTypesClasses

import cl.uchile.dcc.citric.model.Panels.AbstractPanel.AbstractPanel
/**The 'DropPanel' class represents one type of the Panels that are on the board of the game.
 * This Panel extends from the abstract class AbstractPanel, so that we inherit the methods of adding and removing
 * characters from this Panel.
 *When a player lands on it, the amount of their stars will be reduce depending on the result
 * of rollDice
 *
 * @example
 * {{{
 *   var panel1: DropPanel= new DropPanel
 *   panel1.addCharacter(CharacterNini)
 *   panel1.RemoveStars()
 * }}}
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 * */
class DropPanel() extends AbstractPanel {
  /**
   * This function removes stars from the players when they land on a Drop Panel. The amount
   * of stars that are taken away depends on the player's current Norm and the result of
   * rollDice.
   */
  def RemoveStars(): Unit = {
    if (characters.nonEmpty) {
      var i: Int = 0
      while (i < characters.length) {
        val roll: Int = characters(i).rollDice()
        if (characters(i).CurrentStars > roll * characters(i).CurrentNorm) {
          characters(i).CurrentStars -= (roll * characters(i).CurrentNorm)
        }
        else {
          characters(i).CurrentStars = 1
        }
        i += 1
      }
    }
  }
}
