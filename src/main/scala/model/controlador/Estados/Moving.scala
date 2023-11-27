package cl.uchile.dcc.citric
package model.controlador.Estados

import model.controlador.{GameController, GameState}

class Moving(context:GameController) extends GameState(context){
  override def inMoving(): Boolean = true

  override def stopsMoving(): Unit = this.setState(new OnPanel(context))

  override def OutOfMoves(): Unit = this.setState(new OnPanel(context))

}
