package cl.uchile.dcc.citric
package model.panels.paneltypes

import model.panels.abstractpanel.AbstractPanel
import model.panels.`trait`.Panel
import model.units.players.PlayerCharacter

import cl.uchile.dcc.citric.model.controller.GameController

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
class HomePanel(val Owner:PlayerCharacter) extends AbstractPanel{
  val name = "Home Panel"
  /**This method simulates the NormaCheck effect of this panel. When a character makes de NormaCheck
   * it levels up their Norm if they fulfilled the requirements.
   *
   * @param player the character that landed on the panel and it's going to receive the effect
   * @example
   * {{{
   *   var panel2: HomePanel = new HomePanel()
   *   panel2.addCharacter(CharacterHannah)/*The character is in Norm1 and fulfilled the objectives*/
   *   panel2.NormaCheck(CharacterHannah)/*upgrades to Norm2*/
   * }}}*/
  def NormaCheck(player:PlayerCharacter): Unit = {
    if (characters.contains(player)){
        player.CurrentNorm.upgradeNorm(player)
    }
  }
  /**This simulates the effect of the homePanel*/
  override def apply(player: PlayerCharacter, context: GameController): Unit = {
    if(characters.contains(player)){
      player.addHP(1)
      val norm=player.CurrentNorm
      NormaCheck(player)
      if(player.CurrentNorm!=norm)context.askObjectiveNorm(1,player)/*It is set as 1 the selection but with a real input it would work well for choosing*/
    }
  }
}