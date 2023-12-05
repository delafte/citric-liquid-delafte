package cl.uchile.dcc.citric
package model.controlador.states

import model.controlador.{GameController, GameState}
/**The Moving class represents the state Moving of the game. In this state the player is moving on the Board of the game.
 * @param context the Game Controller
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
class Moving(context:GameController) extends GameState(context){
  override def inMoving(): Boolean = true

  override def stopsMoving(): Unit = {
    this.setState(new Combat(context))
  }

  override def OutOfMoves(): Unit = {
    this.setState(new Combat(context))
  }

  override def doAction(choose: Int): Unit = {
    context.followingMoves(choose)
  }
  override def choosePath(): Unit = {
    this.setState(new Moving(context))
  }
}
