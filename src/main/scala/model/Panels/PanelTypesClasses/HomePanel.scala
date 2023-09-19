package cl.uchile.dcc.citric
package model.Panels.PanelTypesClasses

import model.Panels.AbstractPanel.AbstractPanel
import model.Panels.`trait`.Panel
import model.Units.Players.PlayerCharacter

import scala.collection.mutable.ArrayBuffer
/** The 'HomePanel' class represents one type of the Panels that are on the board of the game.
 * This Panel extends from the abstract class AbstractPanel, so that we inherit the methods of adding and removing
 * characters from this Panel.
 * Also, the Home Panel has a single owner, which consists of a PlayerCharacter that can decide whether to continue
 * or to stop at the pass, even if the player has to keep moving forward. If a player that is not the owner lands on this panel,
 * then he can also activate it.
 * When it is activated, the PlayerCharacter recovers HP points and the panel makes a Norm Check.
 *
 * @constructor Creates a new Home Panel with a specified and unique owner.
 *
 * @param Owner The PlayerCharacter that owns the HomePanel. It has to be unique.
 *
 * @example
 * {{{
 *   var home_panel1: HomePanel = new HomePanel(CharacterAnna)
 * }}}
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
/*the method of this class will be implemented later*/
class HomePanel(val Owner:PlayerCharacter) extends AbstractPanel{}