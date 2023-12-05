package cl.uchile.dcc.citric
package model.controlador.states

import model.controlador.{GameController, GameState}
/**The Combat class represents the state combat of the game. In this state the player has to choose to fight or not and who if it
 * is against a another player.
 *
 * @param context the Game Controller
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
*/
class Combat(context:GameController) extends GameState(context){
  override def inCombat(): Boolean = true

  override def doAction(choose:Int): Unit = {
    if(context.encounterPanel && context.numCombat<2){
      context.Attacks()
    }
    else if(!context.encounterPanel && context.numCombat<2){
      val more=context.checkMorePlayersInPanel()
      if(more){
        if(context.AttackDecision(choose)==1){
          context.selectEnemy(choose)
        }
        else context.DecideNotFightCharacter()
      }
      else context.EndCombat()
    }
    else {
      context.numCombat = 0
      context.EndCombat()
    }
  }
  override def Attacks(): Unit = {
    this.setState(new Wait(context))
  }

  override def DecideNotFightCharacter(): Unit = {
    this.setState(new OnPanel(context))
  }

  override def EndCombat(): Unit = this.setState(new OnPanel(context))

}
