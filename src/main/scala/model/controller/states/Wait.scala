package cl.uchile.dcc.citric
package model.controller.states

import model.controller.{GameController, GameState}
/**The Wait class represents the state Wait of the game. In this state the Unities choose their reaction against an attack.
 * Also, the Attack process is triggered here.
 * @constructor creates an Wait State with a specified GameController.
 * @param context the Game Controller
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
class Wait(context:GameController) extends GameState(context){
  override def inWait(): Boolean = true

  override def doAction(choose:Int): Unit = {
    if(context.numCombat == 0){
      context.doAttack(choose,0)
    }
    else if(context.numCombat == 1){
      context.doAttack(choose,1)
    }
    context.response()
  }
  override def response(): Unit = {
    this.setState(new Combat(context))
  }

}
