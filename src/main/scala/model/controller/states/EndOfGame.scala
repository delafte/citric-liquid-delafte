package cl.uchile.dcc.citric
package model.controller.states

import model.controller.{GameController, GameState}
/**The EndOfGame class represents the state EndOfGame of the game. To achieve this state, a character has to reach
 * Norm6.
 * @constructor creates a EndOfGame State with a specified GameController.
 * @param context the Game Controller.
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
class EndOfGame(context:GameController) extends GameState(context){
  override def inEndOfGame(): Boolean = true

}
