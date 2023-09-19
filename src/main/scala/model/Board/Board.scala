package cl.uchile.dcc.citric
package model.Board

import model.Panels.`trait`.Panel
import model.Units.Players.PlayerCharacter

import scala.collection.mutable.ArrayBuffer
/**The class 'Board' represents, as the name says, the board in which the game takes place.
 * The Board consists on a set of different types of panels with a special order. The players
 * have to move through it during the game.
 *
 * @example
 * {{{
 *   var board: Board = new Board(ArrayOfPanels, ArrayOfCharacters)
 *   board.CreateConnections()
 * }}}
 *
 * @param panels An array with all the panels of the board, it must be in order.
 * @param players An array of the characters that are going to be playing the game.
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 * */
class Board(panels: ArrayBuffer[Panel], players: ArrayBuffer[PlayerCharacter]) {
  val Panels: ArrayBuffer[Panel] = panels
  val Players: ArrayBuffer[PlayerCharacter] = players
  /**This function creates the connection between panels by updating their NextPanels value*/
  def CreateConnections():Unit={
    var i:Int = 0
    while (i<Panels.length){
      if(i != 0 && i<(Panels.length)-1){
        var Panel: Panel = Panels(i)
        Panel.nextPanels += (Panels(i-1),Panels(i+1))
      }
      else if(i == 0) Panels(0).nextPanels += (Panels(Panels.length-1),Panels(i+1))
      else Panels(i).nextPanels += (Panels(i-1),Panels(0))
      i+=1
    }
  }

}
