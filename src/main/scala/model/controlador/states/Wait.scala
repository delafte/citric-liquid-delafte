package cl.uchile.dcc.citric
package model.controlador.states

import model.controlador.{GameController, GameState}

class Wait(context:GameController) extends GameState(context){
  override def inWait(): Boolean = true

  override def doAction(choose:Int): Unit = {
    if(context.numCombat == 0){
      context.doAttack(0)
    }
    else if(context.numCombat ==1){
      context.doAttack(1)
    }
    context.response()
  }
  override def response(): Unit = {
    this.setState(new Combat(context))
  }

}
