package cl.uchile.dcc.citric
package model.controlador.Estados

import model.controlador.{GameController, GameState}

class EndOfGame(context:GameController) extends GameState(context){
  override def inEndOfGame(): Boolean = true

}
