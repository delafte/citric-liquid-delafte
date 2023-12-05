package cl.uchile.dcc.citric
package model.boardtest

import model.board.Board
import model.panels.paneltypes.{BonusPanel, NeutralPanel,EncounterPanel,DropPanel, HomePanel}
import model.panels.`trait`.Panel
import model.units.players.PlayerCharacter

import munit.FunSuite

import scala.collection.mutable.ArrayBuffer
class BoardTest extends FunSuite {
  /*
  Here we define the values and variables that are necessary for testing
  our Board Class
  */
  private val Ammy: PlayerCharacter = new PlayerCharacter("Ammy", 10, 5, 2, 1)
  private val Hannah: PlayerCharacter = new PlayerCharacter("Hannah", 15, 2, 5, 8)
  private val characters: ArrayBuffer[PlayerCharacter]=ArrayBuffer(Ammy,Hannah)
  private var board: Board =_

  override def beforeEach(context: BeforeEach): Unit = {
    board = new Board(characters)
  }
  test("A Board has panels connected to each other, also, the fist one connects with the last, the last one with the first") {
    assertEquals(board.panels(0).nextPanels,List[Panel](board.panels(1)))
    assertEquals(board.panels(24).nextPanels, List[Panel](board.panels(0)))
    assertEquals(board.panels(2).nextPanels, List[Panel](board.panels(3), board.panels(9)))
  }
  test("A board has players"){
    assertEquals(board.players, characters)
  }
}

