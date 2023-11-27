package cl.uchile.dcc.citric
package model.controlador.Estados

import model.controlador.{GameController, GameState}

class Wait(context:GameController) extends GameState(context){
  override def inWait(): Boolean = true

  override def Evades(): Unit = this.setState(new Combat(context))

  override def defends(): Unit = this.setState(new Combat(context))


}
