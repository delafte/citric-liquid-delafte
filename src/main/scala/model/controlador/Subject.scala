package cl.uchile.dcc.citric
package model.controlador
import model.norm.Norm

import cl.uchile.dcc.citric.model.units.players.CharacterWinEvent

trait Subject{
  /**Method to add observers to the subject*/
  def addObserverWinEvent(observer: Observer[CharacterWinEvent]): Unit
  //def addObserverDeathEvent(observer: Observer[UnityDeathEvent]): Unit
  /**Method to notify the observers about a value of the subject*/
  def notifyObserversWinEvent(value: CharacterWinEvent): Unit
  //def notifyObserversDeathEvent(value: UnityDeathEvent): Unit
}
