package cl.uchile.dcc.citric
package model.TypeofPanelsTests

import model.Panels.PanelTypesClasses.{HomePanel, NeutralPanel, BonusPanel,DropPanel,EncounterPanel}
import model.Panels.`trait`.Panel
import model.Units.Players.PlayerCharacter

import munit.FunSuite

import scala.collection.mutable.ArrayBuffer
class HomePanelTest extends FunSuite {
  /*
  * Here we define the values and variables that are necessary for testing our
  * HomePanel class.
  */
  private val Nini: PlayerCharacter = new PlayerCharacter("Nini", 10, 5, 2, 1)
  private val Emma: PlayerCharacter = new PlayerCharacter("Emma", 15,2,5,8)
  private val NextPanels: ArrayBuffer[Panel] = ArrayBuffer(new NeutralPanel(),new HomePanel(Emma),new BonusPanel(), new DropPanel, new EncounterPanel)
  private val characters: ArrayBuffer[PlayerCharacter]=ArrayBuffer(Nini,Emma)
  private val Empty: ArrayBuffer[PlayerCharacter]=ArrayBuffer()

  private var homePanel: HomePanel = _
  override def beforeEach(context: BeforeEach): Unit = {
    homePanel = new HomePanel(Nini)
  }
  test("A HomePanel has an owner, and it has to be created with it"){
    assertEquals(homePanel.Owner, Nini)
  }
  test("A HomePanel is connected directly to other panels, we try all possible types"){
    homePanel.addPanel(NextPanels(0))
    homePanel.addPanel(NextPanels(1))
    homePanel.addPanel(NextPanels(2))
    homePanel.addPanel(NextPanels(3))
    homePanel.addPanel(NextPanels(4))
    assertEquals(homePanel.nextPanels, NextPanels)
  }
  test("We can add characters to a HomePanel"){
    homePanel.addCharacter(Nini)
    homePanel.addCharacter(Emma)
    assertEquals(homePanel.characters, characters)
    /*Also, we check the case in which we try to add a character that is already in the panel*/
    homePanel.addCharacter(Emma)
    assertEquals(homePanel.characters, characters)
  }
  test("We can also delete Characters from a HomePanel"){
    /*Testing the case in which we are trying to remove a character from a panel without characters*/
    homePanel.removeCharacter(Nini, homePanel.characters)
    assertEquals(homePanel.characters,Empty)
    /*Testing the normal situation*/
    homePanel.addCharacter(Nini)
    homePanel.addCharacter(Emma)
    homePanel.removeCharacter(Emma,homePanel.characters)
    characters -= Emma
    assertEquals(homePanel.characters,characters)
    /*Testing the case in which we try to delete a character that isn't in the list*/
    homePanel.removeCharacter(Emma, homePanel.characters)
    assertEquals(homePanel.characters,characters)
  }
}
