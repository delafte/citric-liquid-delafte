package cl.uchile.dcc.citric
package model.TypeofPanelsTests

import cl.uchile.dcc.citric.model.Panels.PanelTypesClasses.{EncounterPanel, NeutralPanel}
import cl.uchile.dcc.citric.model.Panels.`trait`.Panel
import cl.uchile.dcc.citric.model.Units.Players.PlayerCharacter

import munit.FunSuite

import scala.collection.mutable.ArrayBuffer
class EncounterPanelTest extends FunSuite {
  /*
  * Here we define the values and variables that are necessary for testing our
  * EncounterPanel class.
  */
  private val Ammy: PlayerCharacter = new PlayerCharacter("Ammy", 10, 5, 2, 1)
  private val Hannah: PlayerCharacter = new PlayerCharacter("Hannah", 15, 2, 5, 8)
  private val NextPanels: ArrayBuffer[Panel] = ArrayBuffer(new NeutralPanel(), new NeutralPanel())
  private var encounterPanel: EncounterPanel = _
  private val characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer(Ammy, Hannah)
  private val Empty: ArrayBuffer[PlayerCharacter] = ArrayBuffer()
  override def beforeEach(context: BeforeEach): Unit = {
    encounterPanel = new EncounterPanel()
  }
  test("An Encounter Panel is connected directly to other panels") {
    encounterPanel.nextPanels += (NextPanels(0), NextPanels(1))
    assertEquals(encounterPanel.nextPanels, NextPanels)
  }
  test("We can add characters to an Encounter Panel") {
    encounterPanel.addCharacter(Ammy)
    encounterPanel.addCharacter(Hannah)
    assertEquals(encounterPanel.characters, characters)
    /*Also, we check the case in which we try to add a character that is already in the panel*/
    encounterPanel.addCharacter(Ammy)
    assertEquals(encounterPanel.characters, characters)
  }
  test("We can also delete Characters from an Encounter Panel") {
    /*Testing the case in which we are trying to remove a character from a panel without characters*/
    encounterPanel.removeCharacter(Ammy, encounterPanel.characters)
    assertEquals(encounterPanel.characters, Empty)
    /*Testing the normal situation*/
    encounterPanel.addCharacter(Ammy)
    encounterPanel.addCharacter(Hannah)
    encounterPanel.removeCharacter(Hannah,encounterPanel.characters)
    characters -= Hannah
    assertEquals(encounterPanel.characters, characters)
  }
}

