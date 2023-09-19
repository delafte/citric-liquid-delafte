package cl.uchile.dcc.citric
package model.Panels.AbstractPanel

import cl.uchile.dcc.citric.model.Panels.`trait`.Panel
import cl.uchile.dcc.citric.model.Units.Players.PlayerCharacter

import scala.collection.mutable.ArrayBuffer
/**
 * The 'AbstractPanel' abstract class consists on two methods that all types of Panels must have: addCharacter and
 * removeCharacter. With this implementation we avoid the repetition of code.
 * This two methods are explained in the trait Panel.
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
abstract class AbstractPanel extends Panel{
  override val characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer()
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
}
