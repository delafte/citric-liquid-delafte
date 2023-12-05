package cl.uchile.dcc.citric
package model.controlador.states

import model.controlador.{GameController, GameState}

class Recovery(context:GameController) extends GameState(context){
  override def inRecovery(): Boolean = true

  override def doAction(choose:Int): Unit = {
    if (context.rollRecovery() >= 6 - context.numChapter) {
      println("Good! the roll was sufficient, now it's your turn")
      context.givePlayerStars()
      context.sufficientRoll()
    }
    else {
      println("Your roll was insufficient! next turn.")
      context.insufficientRoll()
    }
  }
  override def insufficientRoll(): Unit = {
    this.setState(new Chapter(context))
  }

  override def sufficientRoll(): Unit = this.setState(new PlayerTurn(context))//Revisar

}
