package cl.uchile.dcc.citric
package model.BoardTest

import model.Board.Board
import model.Panels.PanelTypesClasses.{BonusPanel, NeutralPanel,EncounterPanel,DropPanel, HomePanel}
import model.Panels.`trait`.Panel
import model.Units.Players.PlayerCharacter

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
  private val GamePanels: ArrayBuffer[Panel]=ArrayBuffer(new HomePanel(Ammy), new NeutralPanel(), new EncounterPanel(), new DropPanel(), new BonusPanel(), new DropPanel())
  private var board: Board =_

  override def beforeEach(context: BeforeEach): Unit = {
    board = new Board(GamePanels,characters)
  }
  test("A Board has panels connected to each other in the order of the input, also, the fist one connects with the last and the second, the last one with the first and the previous") {
    assertEquals(GamePanels(0).nextPanels, List[Panel](GamePanels(5), GamePanels(1)))
    assertEquals(GamePanels(3).nextPanels, List[Panel](GamePanels(2),GamePanels(4)))
    assertEquals(GamePanels(5).nextPanels, List[Panel](GamePanels(4), GamePanels(0)))
  }
  test("A board has players"){
    assertEquals(board.players, characters)
  }
}

