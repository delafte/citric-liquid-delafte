package cl.uchile.dcc.citric
package model.Panels.PanelTypesClasses

import model.Panels.AbstractPanel.AbstractPanel
import model.Units.Players.PlayerCharacter
import model.Panels.`trait`.Panel

/**The 'DropPanel' class represents one type of the Panels that are on the board of the game.
 * This Panel extends from the abstract class AbstractPanel.
 * When players land on it, the amount of their stars will be reduce depending on the result
 * of their rollDice.
 *
 * @constructor Creates a DropPanel.
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
   * This function removes stars from the players when they land on a DropPanel. The amount
   * of stars that are taken away depends on the player's current Norm and the result of
   * rollDice.
   *
   * @example
   * {{{
   *   var panel2: DropPanel = new DropPanel()
   *   panel2.addCharacter(CharacterHannah)
   *   panel2.RemoveStars()
   * }}}
   */
  override def apply(): Unit = {
    if (_characters.nonEmpty) {
      var i: Int = 0
      while (i < _characters.length) {
        val roll: Int = _characters(i).rollDice(_characters(i).randomNumberGenerator)
        if (_characters(i).CurrentStars > (roll * _characters(i).CurrentNorm)) {
          _characters(i).CurrentStars = _characters(i).CurrentStars - (roll*_characters(i).CurrentNorm)
        }
        else {
          _characters(i).CurrentStars = 0
        }
        i += 1
      }
    }
  }
}
