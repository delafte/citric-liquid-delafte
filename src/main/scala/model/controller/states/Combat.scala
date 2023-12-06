package cl.uchile.dcc.citric
package model.controller.states

import model.controller.{GameController, GameState}
/**The Combat class represents the state combat of the game. In this state the player has to choose to fight or not and who if it
 * is against a another player.
 * @constructor creates a Combat State with a specified GameController.
 * @param context the Game Controller
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
*/
class Combat(context:GameController) extends GameState(context){
  override def inCombat(): Boolean = true

  override def doAction(choose:Int): Unit = {
    if(context.encounterPanel && context.numCombat<2){
      context.attacks()
    }
    else if(!context.encounterPanel && context.numCombat<2){
      val more=context.checkMorePlayersInPanel()
      if(more){
        if(context.attackDecision(choose)==1){
          context.selectEnemy(choose)
        }
        else context.decideNotFightCharacter()
      }
      else context.endCombat()
    }
    else {
      context.numCombat = 0
      context.endCombat()
    }
  }
  override def attacks(): Unit = {
    this.setState(new Wait(context))
  }

  override def decideNotFightCharacter(): Unit = {
    this.setState(new OnPanel(context))
  }

  override def endCombat(): Unit = {
    context.numCombat = 0
    this.setState(new OnPanel(context))
  }

}
