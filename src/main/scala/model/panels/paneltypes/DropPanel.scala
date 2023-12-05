package cl.uchile.dcc.citric
package model.panels.paneltypes

import model.panels.abstractpanel.AbstractPanel
import model.units.players.PlayerCharacter

import cl.uchile.dcc.citric.model.controlador.GameController

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
  val name="Drop Panel"
  /**
   * This function removes stars from the players when they land on a DropPanel. The amount
   * of stars that are taken away depends on the player's current Norm and the result of
   * rollDice.
   *
   * @example
   * {{{
   *   var panel2: DropPanel = new DropPanel()
   *   panel2.addCharacter(CharacterHannah)
   *   panel2.apply(CharacterHannah)
   * }}}
   */
  override def apply(player: PlayerCharacter, context: GameController): Unit = {
    if (characters.contains(player)) {
        val roll: Int = player.rollDice()
          player.removeStars(roll*player.CurrentNorm.NumberNorm)
    }
  }
}
