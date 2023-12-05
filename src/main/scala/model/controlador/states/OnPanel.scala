package cl.uchile.dcc.citric
package model.controlador.states

import model.controlador.{GameController, GameState}
/**The OnPanel class represents the state OnPanel of the game. In this state the player is on a panel of the Board of the game
 * and they receive the panel's effect.
 * @constructor creates an OnPanel State with a specified GameController.
 * @param context the Game Controller
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
class OnPanel(context:GameController) extends GameState(context){
  override def inOnPanel(): Boolean = true

  override def doAction(choose: Int): Unit = {
    if(context.encounterPanel) context.effectApplied()
    context.effectPanel()
  }

  override def fightWildUnit(): Unit = {
    this.setState(new Combat(context))
  }
  override def effectApplied(): Unit = {
    this.setState(new Chapter(context))
    context.encounterPanel = false
    context.nextTurn()
  }
  override def reachNorm6(): Unit = this.setState(new EndOfGame(context))

}
