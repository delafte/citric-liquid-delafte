package cl.uchile.dcc.citric
package model.TypeofPanelsTests

import model.Panels.PanelTypesClasses.{HomePanel, NeutralPanel, BonusPanel,DropPanel,EncounterPanel}
import model.Panels.`trait`.Panel
import model.Units.Players.PlayerCharacter

import munit.FunSuite

import scala.collection.mutable.ArrayBuffer
class NeutralPanelTest extends FunSuite {
  /*
  * Here we define the values and variables that are necessary for testing our
  * NeutralPanel class.
  */
  private val Nini: PlayerCharacter = new PlayerCharacter("Nini", 10, 5, 2, 1)
  private val Emma: PlayerCharacter = new PlayerCharacter("Emma", 15,2,5,8)
  private val NextPanels: ArrayBuffer[Panel] = ArrayBuffer(new NeutralPanel(),new HomePanel(Emma),new BonusPanel(), new DropPanel, new EncounterPanel)
  private val characters: ArrayBuffer[PlayerCharacter]=ArrayBuffer(Nini,Emma)
  private val Empty: ArrayBuffer[PlayerCharacter]=ArrayBuffer()

  private var neutralPanel: NeutralPanel = _
  override def beforeEach(context: BeforeEach): Unit = {
    neutralPanel = new NeutralPanel()
  }
  test("A NeutralPanel is connected directly to other panels, we try all possible types "){
    neutralPanel.addPanel(NextPanels(0))
    neutralPanel.addPanel(NextPanels(1))
    neutralPanel.addPanel(NextPanels(2))
    neutralPanel.addPanel(NextPanels(3))
    neutralPanel.addPanel(NextPanels(4))
    assertEquals(neutralPanel.nextPanels, NextPanels)
  }
  test("We can add characters to a NeutralPanel"){
    neutralPanel.addCharacter(Nini)
    neutralPanel.addCharacter(Emma)
    assertEquals(neutralPanel.characters, characters)
    /*Also, we check the case in which we try to add a character that is already in the panel*/
    neutralPanel.addCharacter(Emma)
    assertEquals(neutralPanel.characters, characters)
  }
  test("We can also delete Characters from a NeutralPanel"){
    /*Testing the case in which we are trying to remove a character from a panel without characters*/
    neutralPanel.removeCharacter(Nini, neutralPanel.characters)
    assertEquals(neutralPanel.characters,Empty)
    /*Testing the normal situation*/
    neutralPanel.addCharacter(Nini)
    neutralPanel.addCharacter(Emma)
    neutralPanel.removeCharacter(Emma,neutralPanel.characters)
    characters -= Emma
    assertEquals(neutralPanel.characters,characters)
    /*Testing the case in which we try to delete a character that isn't in the list*/
    neutralPanel.removeCharacter(Emma, neutralPanel.characters)
    assertEquals(neutralPanel.characters, characters)
  }
}
