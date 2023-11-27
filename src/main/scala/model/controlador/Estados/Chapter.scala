package cl.uchile.dcc.citric
package model.controlador.Estados

import model.controlador.{GameController, GameState}

class Chapter(context:GameController) extends GameState(context){
  override def inChapter() = true

  override def NewChapter(): Unit = this.setState(new Chapter(context))

  override def StartTurnPlayer(): Unit = this.setState(new PlayerTurn(context))

  override def ReachNorm6(): Unit = this.setState(new EndOfGame(context))
}
