package cl.uchile.dcc.citric
package model.panels.abstractpanel

import model.panels.`trait`.Panel
import model.units.players.PlayerCharacter

import cl.uchile.dcc.citric.model.controlador.GameController

import scala.collection.mutable.ArrayBuffer
/**
 * The 'AbstractPanel' abstract class consists on three methods that all types of Panels must have: addCharacter,
 * removeCharacter and addPanel, also it considers the characters and nextPanels Arrays. With this implementation we avoid the repetition of code.
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
abstract class AbstractPanel extends Panel{
  /** Array of the characters currently positioned on this panel.
   *
   * In the game, multiple characters might be on the same panel at once, e.g., if multiple players
   * land on the same space.
   */
  protected val _characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer()
  /** An array of panels that are directly connected to this one.
   *
   * In the context of the game, multiple routes or paths may exist, this could represent the
   * possible next steps a player might take after being on this panel.
   *
   * @return a List of Panel instances that are adjacent or connected to this panel.
   */
  protected var _nextPanels: ArrayBuffer[Panel] = ArrayBuffer()
  /**Returns an immutable list of the characters on the panel*/
  def characters: List[PlayerCharacter] = _characters.toList
  /**Returns an immutable list of the adjacent panels of the panel*/
  def nextPanels: List[Panel] = _nextPanels.toList
  override def addCharacter(player: PlayerCharacter): Unit = {
    if(!_characters.contains(player))
    _characters.append(player)
  }
  override def removeCharacter(player: PlayerCharacter): Unit = {
    if(_characters.nonEmpty){
      _characters-=player
    }
  }

  override def addPanel(panel: Panel): Unit = {
    _nextPanels += panel
  }
  override def removePanel(panel:Panel): Unit = {
    if(_nextPanels.nonEmpty && _nextPanels.contains(panel)){
      _nextPanels -= panel
    }
  }
  def apply(player: PlayerCharacter, context: GameController): Unit ={}
}
