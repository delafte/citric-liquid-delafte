package cl.uchile.dcc.citric
package model.controlador.states

import model.controlador.{GameController, GameState}
/**The PlayerTurn class represents the state Player of the game. In this state the player has to roll the Dice to
 * begin later their movement.
 * @constructor creates an PlayerTurn State with a specified GameController.
 * @param context the Game Controller
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
class PlayerTurn(context:GameController) extends GameState(context){
  override def inPlayerTurn(): Boolean = true

  override def doAction(choose: Int): Unit = {
    context.playerRollDice()
    context.rollD()
  }
  override def rollD(): Unit = {
    this.setState(new Moving(context))
  }
}
