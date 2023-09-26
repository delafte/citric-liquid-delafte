package cl.uchile.dcc.citric
package model.Board

import model.Panels.`trait`.Panel
import model.Units.Players.PlayerCharacter

import scala.collection.mutable.ArrayBuffer
/**The class 'Board' represents the board in which the game takes place.
 * The Board consists on a set of different classes of panels with a special order. The players
 * have to move through it during the game.
 *
 * @constructor Creates a Board with certain characters and a specified configuration of panels
 *
 * @example
 * {{{
 *   var board: Board = new Board(ArrayOfPanels, ArrayOfCharacters)
 *   board.CreateConnections()
 * }}}
 *
 * @param Panels An array with all the panels of the board, it must be in order.
 * @param players An array of the characters that are going to be playing the game.
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 * */
class Board(val Panels: ArrayBuffer[Panel], val players: ArrayBuffer[PlayerCharacter]) {

  /**This function creates the connections between panels by updating their NextPanels value, using the addPanel method.*/
  def CreateConnections():Unit={
    var i:Int = 0
    while (i<Panels.length){
      if(i != 0 && i<(Panels.length)-1){
        val Panel: Panel = Panels(i)
        Panel.addPanel(Panels(i-1))
        Panel.addPanel(Panels(i+1))
      }
      else if(i == 0){
        Panels(0).addPanel(Panels(Panels.length-1))
        Panels(0).addPanel(Panels(i+1))
      }
      else{
        Panels(i).addPanel(Panels(i-1))
        Panels(i).addPanel(Panels(0))
      }
      i+=1
    }
  }

}
