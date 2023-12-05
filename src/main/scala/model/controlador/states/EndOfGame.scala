package cl.uchile.dcc.citric
package model.controlador.states

import model.controlador.{GameController, GameState}
/**The EndOfGame class represents the state EndOfGame of the game. To reach this state, a character has to reach
 * Norm6.
 * @param context the Game Controller
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
class EndOfGame(context:GameController) extends GameState(context){
  override def inEndOfGame(): Boolean = true

}
