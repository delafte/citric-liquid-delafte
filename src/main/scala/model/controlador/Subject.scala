package cl.uchile.dcc.citric
package model.controlador
import model.norm.Norm

import cl.uchile.dcc.citric.model.units.players.CharacterWinEvent
/**The Subject trait establishes the necessary methods that the Observer Pattern declares for it. It consists only of two
 * that are for adding observers and to notify them on the value of interest. In this case, is the CharacterWinEvent.
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]*/
trait Subject{
  /**Method to add observers to the subject*/
  def addObserverWinEvent(observer: Observer[CharacterWinEvent]): Unit
  //def addObserverDeathEvent(observer: Observer[UnityDeathEvent]): Unit
  /**Method to notify the observers about a value of the subject*/
  def notifyObserversWinEvent(value: CharacterWinEvent): Unit
  //def notifyObserversDeathEvent(value: UnityDeathEvent): Unit
}
