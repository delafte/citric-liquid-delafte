package cl.uchile.dcc.citric
package model.controlador.states

import model.controlador.{GameController, GameState}

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
