package cl.uchile.dcc.citric
package model.controller.states

import model.controller.{GameController, GameState}
/**The Recovery class represents the state Recovery of the game. In this state the player has to roll the Dice
 * to leave the state KO and play their turn, if the roll isn't sufficient, the game skips their turn.
 * @constructor creates an Recovery State with a specified GameController.
 * @param context the Game Controller
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
class Recovery(context:GameController) extends GameState(context){
  override def inRecovery(): Boolean = true

  override def doAction(choose:Int): Unit = {
    if (context.rollRecovery() >= 6 - context.numChapter) {
      println("Good! the roll was sufficient, now it's your turn")
      context.currentPlayer.KO = false
      context.givePlayerStars()
      context.sufficientRoll()
    }
    else {
      println("Your roll was insufficient! next turn.")
      context.insufficientRoll()
      context.nextTurn()

    }
  }
  override def insufficientRoll(): Unit = {
    this.setState(new Chapter(context))
  }

  override def sufficientRoll(): Unit = this.setState(new PlayerTurn(context))//Revisar

}
