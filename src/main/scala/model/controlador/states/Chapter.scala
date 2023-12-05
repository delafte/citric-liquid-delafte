package cl.uchile.dcc.citric
package model.controlador.states

import model.controlador.{GameController, GameState}
/**The Chapter class represents the state chapter of the game. In this state the turns are asigned and it represents
 * a round of the game. Every character ends it's turn in this state.
 * @param context the Game Controller
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
class Chapter(context:GameController) extends GameState(context){
  override def inChapter() = true

  override def NewChapter(): Unit ={
    context.numChapterUpdate()
    this.setState(new Chapter(context))
  }

  override def doAction(choose: Int): Unit = {
    val result: Boolean = context.checkKO()
    val name = context.name
    if(result){
      println(s"O no! $name is KO, directly to Recovery phase")
      context.isKO()
    }
    else{
      println(s"$name's turn")
      context.StartTurnPlayer()
    }

  }

  override def PlayerKO(): Unit = {
    this.setState(new Recovery(context))
  }

  override def StartTurnPlayer(): Unit = {
      context.givePlayerStars()
      this.setState(new PlayerTurn(context))
  }

  override def isKO(): Unit = {
    this.setState(new Recovery(context))
  }
  override def ReachNorm6(): Unit = this.setState(new EndOfGame(context))
}