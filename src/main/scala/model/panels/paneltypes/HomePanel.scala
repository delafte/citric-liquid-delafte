package cl.uchile.dcc.citric
package model.panels.paneltypes

import model.panels.abstractpanel.AbstractPanel
import model.panels.`trait`.Panel
import model.units.players.PlayerCharacter

import scala.collection.mutable.ArrayBuffer
/** The 'HomePanel' class represents one type of the Panels that are on the board of the game.
 * This Panel extends from the abstract class AbstractPanel.
 * Also, the HomePanel has a single owner, which consists of a PlayerCharacter that can decide whether to continue
 * or to stop at the pass, even if the player has to keep moving forward. If a player that is not the owner lands on this panel,
 * then he can also activate it.
 * When it is activated, the PlayerCharacter recovers HP points and the panel makes a Norm Check.
 *
 * @constructor Creates a new HomePanel with a specified and unique owner.
 *
 * @param Owner The PlayerCharacter that owns the HomePanel.
 *
 * @example
 * {{{
 *   var home_panel1: HomePanel = new HomePanel(CharacterAnna)
 * }}}
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
/*the method of this class will be implemented later*/
class HomePanel(val Owner:PlayerCharacter) extends AbstractPanel{
  /**This method simulates the NormaCheck effect of this panel. When a character makes de NormaCheck
   * it levels up their Norm if they fulfilled the requirements*/
  def NormaCheck(player:PlayerCharacter): Unit = {
    if (characters.contains(player)){
        player.CurrentNorm.upgradeNorm(player)
    }
  }
}