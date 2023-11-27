package cl.uchile.dcc.citric
package model.controlador.Estados

import model.controlador.{GameController, GameState}

class Recovery(context:GameController) extends GameState(context){
  override def inRecovery(): Boolean = true

  override def insufficientRoll(): Unit = this.setState(new Chapter(context))

  override def sufficientRoll(): Unit = this.setState(new Chapter(context))//Revisar

}
