package cl.uchile.dcc.citric
package model.TypeofPanelsTests

import model.Panels.PanelTypesClasses.{EncounterPanel, NeutralPanel, BonusPanel, DropPanel,HomePanel}
import model.Panels.`trait`.Panel
import model.Units.Players.PlayerCharacter

import munit.FunSuite

import scala.collection.mutable.ArrayBuffer
class EncounterPanelTest extends FunSuite {
  /*
  * Here we define the values and variables that are necessary for testing our
  * EncounterPanel class.
  */
  private val Ammy: PlayerCharacter = new PlayerCharacter("Ammy", 10, 5, 2, 1)
  private val Hannah: PlayerCharacter = new PlayerCharacter("Hannah", 15, 2, 5, 8)
  private val NextPanels: ArrayBuffer[Panel] = ArrayBuffer(new NeutralPanel(), new BonusPanel(), new EncounterPanel(), new DropPanel(),new HomePanel(Ammy))
  private val characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer(Ammy, Hannah)
  private val Empty: ArrayBuffer[PlayerCharacter] = ArrayBuffer()

  private var encounterPanel: EncounterPanel = _
  override def beforeEach(context: BeforeEach): Unit = {
    encounterPanel = new EncounterPanel()
  }
  test("An Encounter Panel is connected directly to other panels, we try all possible types") {
    encounterPanel.addPanel(NextPanels(0))
    encounterPanel.addPanel(NextPanels(1))
    encounterPanel.addPanel(NextPanels(2))
    encounterPanel.addPanel(NextPanels(3))
    encounterPanel.addPanel(NextPanels(4))
    assertEquals(encounterPanel.nextPanels, NextPanels.toList)
  }
  test("We can add characters to an Encounter Panel") {
    encounterPanel.addCharacter(Ammy)
    encounterPanel.addCharacter(Hannah)
    assertEquals(encounterPanel.characters, characters.toList)
    /*Also, we check the case in which we try to add a character that is already in the panel*/
    encounterPanel.addCharacter(Ammy)
    assertEquals(encounterPanel.characters, characters.toList)
  }
  test("We can also delete Characters from an Encounter Panel") {
    /*Testing the case in which we are trying to remove a character from a panel without characters*/
    encounterPanel.removeCharacter(Ammy)
    assertEquals(encounterPanel.characters, Empty.toList)
    /*Testing the normal situation*/
    encounterPanel.addCharacter(Ammy)
    encounterPanel.addCharacter(Hannah)
    encounterPanel.removeCharacter(Hannah)
    characters -= Hannah
    assertEquals(encounterPanel.characters, characters.toList)
    /*Testing the case in which we try to delete a character that isn't in the array*/
    encounterPanel.removeCharacter(Hannah)
    assertEquals(encounterPanel.characters, characters.toList)
  }
}

