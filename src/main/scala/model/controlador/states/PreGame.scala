package cl.uchile.dcc.citric
package model.controlador.states

import model.controlador.{GameController, GameState}
/**The PreGame class represents the state PreGame of the game. The GameController is initialized with this state
 * and when it starts, it transitions to the next one.
 * @constructor creates an PreGame State with a specified GameController.
 * @param context the Game Controller
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
class PreGame(context: GameController) extends GameState(context) {
  override def inPreGame() = true
  override def gameStarts(): Unit = {
    this.setState(new Chapter(context))
  }
}
