package cl.uchile.dcc.citric
package model.Panels.AbstractPanel

import cl.uchile.dcc.citric.model.Panels.`trait`.Panel
import cl.uchile.dcc.citric.model.Units.Players.PlayerCharacter

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
  override val characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer()
  /** An array of panels that are directly connected to this one.
   *
   * In the context of the game, multiple routes or paths may exist, this could represent the
   * possible next steps a player might take after being on this panel.
   *
   * @return a List of Panel instances that are adjacent or connected to this panel.
   */
  override var nextPanels: ArrayBuffer[Panel] = ArrayBuffer()
  override def addCharacter(player: PlayerCharacter): Unit = {
    if(!characters.contains(player))
    characters.append(player)
  }
  override def removeCharacter(player: PlayerCharacter,characters: ArrayBuffer[PlayerCharacter]): Unit = {
    if(characters.nonEmpty){
      characters-=player
    }
  }

  override def addPanel(panel: Panel): Unit = {
    nextPanels += panel
  }
}
