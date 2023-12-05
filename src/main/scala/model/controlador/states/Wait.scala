package cl.uchile.dcc.citric
package model.controlador.states

import model.controlador.{GameController, GameState}
/**The Wait class represents the state Wait of the game. In this state the Unities choose their reaction against an attack.
 * Also, the Attack process is triggered here.
 *
 * @param context the Game Controller
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
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
