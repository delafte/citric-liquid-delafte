package cl.uchile.dcc.citric
package model.controlador.Estados

import model.controlador.{GameController, GameState}

class Combat(context:GameController) extends GameState(context){
  override def inCombat(): Boolean = true

  override def Attacks(): Unit = this.setState(new Wait(context))

  override def EndCombat(): Unit = this.setState(new OnPanel(context))

}
