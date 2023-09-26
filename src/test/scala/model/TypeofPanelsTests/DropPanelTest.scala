package cl.uchile.dcc.citric
package model.TypeofPanelsTests

import cl.uchile.dcc.citric.model.Panels.PanelTypesClasses.{DropPanel, NeutralPanel, EncounterPanel, BonusPanel,HomePanel}
import cl.uchile.dcc.citric.model.Panels.`trait`.Panel
import cl.uchile.dcc.citric.model.Units.Players.PlayerCharacter

import munit.FunSuite

import scala.collection.mutable.ArrayBuffer
class DropPanelTest extends FunSuite {
  /*
  * Here we define the values and variables that are necessary for testing our
  * DropPanel class.
  */

  private val Minie: PlayerCharacter = new PlayerCharacter("Minie", 10, 5, 2, 1)
  private val Anna: PlayerCharacter = new PlayerCharacter("Anna", 15, 2, 5, 8)
  private val NextPanels: ArrayBuffer[Panel] = ArrayBuffer(new NeutralPanel(), new BonusPanel(), new EncounterPanel(), new DropPanel, new HomePanel(Minie))
  private var DropPanel: DropPanel = _
  private val characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer(Minie, Anna)
  private val Empty: ArrayBuffer[PlayerCharacter] = ArrayBuffer()
  override def beforeEach(context: BeforeEach): Unit = {
    DropPanel = new DropPanel()
  }

  test("A Drop Panel is connected directly to other panels") {
    DropPanel.addPanel(NextPanels(0))
    DropPanel.addPanel(NextPanels(1))
    DropPanel.addPanel(NextPanels(2))
    DropPanel.addPanel(NextPanels(3))
    DropPanel.addPanel(NextPanels(4))
    assertEquals(DropPanel.nextPanels, NextPanels)
  }
  test("We can add characters to a Drop Panel") {
    DropPanel.addCharacter(Minie)
    DropPanel.addCharacter(Anna)
    assertEquals(DropPanel.characters, characters)
    /*Also, we check the case in which we try to add a character that is already in the panel*/
    DropPanel.addCharacter(Anna)
    assertEquals(DropPanel.characters, characters)
  }
  test("We can also delete Characters from a Drop Panel") {
    /*Testing the case in which we are trying to remove a character from a panel without characters*/
    DropPanel.removeCharacter(Minie, DropPanel.characters)
    assertEquals(DropPanel.characters, Empty)
    /*Testing the normal case*/
    DropPanel.addCharacter(Minie)
    DropPanel.addCharacter(Anna)
    DropPanel.removeCharacter(Anna, DropPanel.characters)
    characters -= Anna
    assertEquals(DropPanel.characters, characters)
  }
  test("If players land on a Drop Panel, it must remove stars from them"){
    DropPanel.addCharacter(Minie)
    /*First, we test that the panel removes stars of the character in a normal situation*/
    Minie.CurrentStars = 10
    val Stars: Int = Minie.CurrentStars
    DropPanel.RemoveStars()
    assert(Minie.CurrentStars <= Stars)
    /*Then, we test an edge case, in which the player has less stars than the quantity that the panel wants to take*/
    Minie.CurrentStars = 1
    DropPanel.RemoveStars()
    assert(Minie.CurrentStars == 1)
  }

}
