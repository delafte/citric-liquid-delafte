package cl.uchile.dcc.citric
package model.controlador.Estados

import model.controlador.{GameController, GameState}

class PlayerTurn(context:GameController) extends GameState(context){
  override def inPlayerTurn(): Boolean = true

  override def RollDice_ChoosePath(): Unit = this.setState(new Moving(context))

  override def PlayerKO(): Unit = this.setState(new Recovery(context))
}
