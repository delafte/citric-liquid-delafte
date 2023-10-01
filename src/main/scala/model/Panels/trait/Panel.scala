package cl.uchile.dcc.citric
package model.Panels.`trait`

import cl.uchile.dcc.citric.model.Units.Players.PlayerCharacter
import scala.collection.mutable.ArrayBuffer

/** The 'Panel' Trait represents a single cell on a board, known as a Panel.
  *
  * Each panel has its own effect depending on its type, which can be applied to a character.
  * In the context of the board game, a panel represents a tile or space that a character lands on
  * and experiences an effect (e.g., losing stars, fighting an enemy, etc.).
  * Panels can also be connected to other panels, allowing for the formation of complex board
  * structures.
  *
  * @author [[https://github.com/r8vnhill Ignacio Slater M.]]
  * @author [[https://github.com/delafte Delaney Tello E.]]
  */
trait Panel {

  /** Array of the characters currently positioned on this panel.
    *
    * In the game, multiple characters might be on the same panel at once, e.g., if multiple players
    * land on the same space.
    */
  protected val _characters: ArrayBuffer[PlayerCharacter]

  /** An array of panels that are directly connected to this one.
   *
   * In the context of the game, multiple routes or paths may exist, this could represent the
   * possible next steps a player might take after being on this panel.
   *
   * @return a List of Panel instances that are adjacent or connected to this panel.
   */
  protected var _nextPanels: ArrayBuffer[Panel]
  /**A getter for the characters on this panel*/
  def characters: List[PlayerCharacter]
  /**A getter for the adjacent panels of this panel*/
  def nextPanels: List[Panel]

  /** Adds a character to the list of characters currently on this panel.
    *
    * This might be invoked when a player moves to this panel or starts their turn on it.
    *
    * @param player The player character to add to this panel.
    */
  def addCharacter(player: PlayerCharacter): Unit

  /** Removes a character from the list of characters currently on this panel.
    *
    * This might be invoked when a player moves off this panel.
    *
    * @param player The player character to remove from this panel.
    */
  def removeCharacter(player: PlayerCharacter): Unit

  /**Adds an adjacent panel to the list of adjacent panels of the current panel.
   * this has to be invoked when a board is created
   * @param panel The panel that is going to be adjacent to the panel
   */
  def addPanel(panel: Panel): Unit
}
