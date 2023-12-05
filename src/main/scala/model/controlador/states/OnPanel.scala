package cl.uchile.dcc.citric
package model.controlador.states

import model.controlador.{GameController, GameState}

class OnPanel(context:GameController) extends GameState(context){
  override def inOnPanel(): Boolean = true

  override def doAction(choose: Int): Unit = {
    if(context.encounterPanel) context.applyEffect()
    context.effectPanel()
  }

  override def fightWildUnit(): Unit = {
    this.setState(new Combat(context))
  }
  override def applyEffect(): Unit = {
    this.setState(new Chapter(context))
    context.encounterPanel = false
    context.nextTurn()
  }
}
