package cl.uchile.dcc.citric
package model.controlador.Estados

import model.controlador.{GameController, GameState}

class OnPanel(context:GameController) extends GameState(context){
  override def inOnPanel(): Boolean = true

  override def DecideFightCharacter(): Unit = this.setState(new Combat(context))

  override def OnEncounterPanel(): Unit = this.setState(new Combat(context))

  override def applyEffect(): Unit = this.setState(new Chapter(context))

  override def FinishEncounterPanel(): Unit = this.setState(new Chapter(context))

}
