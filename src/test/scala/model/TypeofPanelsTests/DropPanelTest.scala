package cl.uchile.dcc.citric
package model.TypeofPanelsTests

import model.Panels.PanelTypesClasses.{DropPanel, NeutralPanel, EncounterPanel, BonusPanel,HomePanel}
import model.Panels.`trait`.Panel
import model.Units.Players.PlayerCharacter

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
  private val characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer(Minie, Anna)
  private val Empty: ArrayBuffer[PlayerCharacter] = ArrayBuffer()

  private var dropPanel: DropPanel = _
  override def beforeEach(context: BeforeEach): Unit = {
    dropPanel = new DropPanel()
  }

  test("A Drop Panel is connected directly to other panels") {
    dropPanel.addPanel(NextPanels(0))
    dropPanel.addPanel(NextPanels(1))
    dropPanel.addPanel(NextPanels(2))
    dropPanel.addPanel(NextPanels(3))
    dropPanel.addPanel(NextPanels(4))
    assertEquals(dropPanel.nextPanels, NextPanels.toList)
  }
  test("We can add characters to a Drop Panel") {
    dropPanel.addCharacter(Minie)
    dropPanel.addCharacter(Anna)
    assertEquals(dropPanel.characters, characters.toList)
    /*Also, we check the case in which we try to add a character that is already in the panel*/
    dropPanel.addCharacter(Anna)
    assertEquals(dropPanel.characters, characters.toList)
  }
  test("We can also delete Characters from a Drop Panel") {
    /*Testing the case in which we are trying to remove a character from a panel without characters*/
    dropPanel.removeCharacter(Minie)
    assertEquals(dropPanel.characters, Empty.toList)
    /*Testing the normal case*/
    dropPanel.addCharacter(Minie)
    dropPanel.addCharacter(Anna)
    dropPanel.removeCharacter(Anna)
    characters -= Anna
    assertEquals(dropPanel.characters, characters.toList)
    /*Testing the case in which we try to delete a character that isn't in the array*/
    dropPanel.removeCharacter(Anna)
    assertEquals(dropPanel.characters, characters.toList)
  }
  test("If players land on a Drop Panel, it must remove stars from them"){
    dropPanel.addCharacter(Minie)
    dropPanel.addCharacter(Anna)
    /*First, we test that the panel removes stars of the character in a normal situation*/
    Minie.CurrentStars_=(10)
    Anna.CurrentStars_=(6)
    dropPanel.RemoveStars()
    assert(Minie.CurrentStars < 10)
    assert(Anna.CurrentStars < 6)
    /*Then, we test an edge case, in which the player has less stars than the quantity that the panel wants to take*/
    Minie.CurrentStars_=(0)
    dropPanel.RemoveStars()
    assert(Minie.CurrentStars == 0)
  }

}
