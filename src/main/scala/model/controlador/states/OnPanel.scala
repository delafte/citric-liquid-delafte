package cl.uchile.dcc.citric
package model.controlador.states

import model.controlador.{GameController, GameState}
/**The OnPanel class represents the state OnPanel of the game. In this state the player is on a panel of the Board of the game
 * and they receive the panel's effect.
 *
 * @param context the Game Controller
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
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
