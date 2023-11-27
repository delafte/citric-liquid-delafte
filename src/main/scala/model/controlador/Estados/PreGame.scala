package cl.uchile.dcc.citric
package model.controlador.Estados

import model.controlador.{GameController, GameState}

class PreGame(context: GameController) extends GameState(context) {
  override def inPreGame() = true

  override def GameStarts(): Unit = this.setState(new Chapter(context))
}
