package cl.uchile.dcc.citric
package model.board

import model.panels.`trait`.Panel
import model.units.players.PlayerCharacter

import cl.uchile.dcc.citric.model.panels.paneltypes.{BonusPanel, DropPanel, EncounterPanel, NeutralPanel}

import scala.collection.mutable.ArrayBuffer
import scala.util.Random
/**The class 'Board' represents the board in which the game takes place.
 * The Board consists on a set of different classes of panels with a special order. The players
 * have to move through it during the game.
 *
 * @constructor Creates a Board with certain characters and a specified configuration of panels
 *
 * @example
 * {{{
 *   var board: Board = new Board(ArrayOfCharacters)
 * }}}
 *
 * @param players An array of the characters that are going to be playing the game.
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 * */
class Board( val players: ArrayBuffer[PlayerCharacter]) {
   val panels: ArrayBuffer[Panel] = ArrayBuffer(new NeutralPanel, new EncounterPanel, new NeutralPanel, new EncounterPanel, new DropPanel,
                                               new NeutralPanel, new BonusPanel, new NeutralPanel, new DropPanel, new DropPanel,
                                                new NeutralPanel, new NeutralPanel, new BonusPanel, new EncounterPanel, new DropPanel,
                                                new NeutralPanel, new BonusPanel, new BonusPanel, new DropPanel, new EncounterPanel,
                                                new NeutralPanel, new NeutralPanel, new NeutralPanel, new EncounterPanel, new DropPanel)
  var i = 0
  while(i<players.length){
    panels(i*2) = players(i).homePanel
    i+=1
  }
  panels(0).addPanel(panels(1))
  panels(1).addPanel(panels(2))
  panels(2).addPanel(panels(3))
  panels(3).addPanel(panels(4))
  panels(4).addPanel(panels(5))
  panels(5).addPanel(panels(6))
  panels(6).addPanel(panels(7))
  panels(7).addPanel(panels(8))
  panels(2).addPanel(panels(9))
  panels(9).addPanel(panels(10))
  panels(10).addPanel(panels(11))
  panels(11).addPanel(panels(12))
  panels(8).addPanel(panels(12))
  panels(12).addPanel(panels(13))
  panels(13).addPanel(panels(14))
  panels(14).addPanel(panels(15))
  panels(15).addPanel(panels(16))
  panels(16).addPanel(panels(17))
  panels(17).addPanel(panels(18))
  panels(18).addPanel(panels(19))
  panels(19).addPanel(panels(20))
  panels(20).addPanel(panels(21))
  panels(21).addPanel(panels(22))
  panels(22).addPanel(panels(23))
  panels(23).addPanel(panels(24))
  panels(24).addPanel(panels(0))

}
