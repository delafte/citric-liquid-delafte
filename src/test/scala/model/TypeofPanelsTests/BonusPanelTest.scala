package cl.uchile.dcc.citric
package model.TypeofPanelsTests
import cl.uchile.dcc.citric.model.Panels.PanelTypesClasses.{BonusPanel, NeutralPanel,EncounterPanel,DropPanel, HomePanel}
import cl.uchile.dcc.citric.model.Panels.`trait`.Panel
import cl.uchile.dcc.citric.model.Units.Players.PlayerCharacter

import munit.FunSuite

import scala.collection.mutable.ArrayBuffer
class BonusPanelTest extends FunSuite {
  /*
  * Here we define the values and variables that are necessary for testing our
  * BonusPanel class.
  */

  private val Nini: PlayerCharacter = new PlayerCharacter("Nini", 10, 5, 2, 1)
  private val Emma: PlayerCharacter = new PlayerCharacter("Emma", 15,2,5,8)
  private val NextPanels: ArrayBuffer[Panel] = ArrayBuffer(new EncounterPanel(),new NeutralPanel(), new DropPanel(), new BonusPanel,new HomePanel(Nini))
  private var BonusPanel: BonusPanel = _
  private val characters: ArrayBuffer[PlayerCharacter]=ArrayBuffer(Nini,Emma)
  private val Empty: ArrayBuffer[PlayerCharacter] = ArrayBuffer()
  override def beforeEach(context: BeforeEach): Unit = {
    BonusPanel = new BonusPanel()
  }
  test("A Bonus Panel is connected directly to other panels"){
    BonusPanel.addPanel(NextPanels(0))
    BonusPanel.addPanel(NextPanels(1))
    BonusPanel.addPanel(NextPanels(2))
    BonusPanel.addPanel(NextPanels(3))
    BonusPanel.addPanel(NextPanels(4))
    assertEquals(BonusPanel.nextPanels, NextPanels)
  }
  test("We can add characters to a BonusPanel"){
    BonusPanel.addCharacter(Nini)
    BonusPanel.addCharacter(Emma)
    assertEquals(BonusPanel.characters, characters)
    /*Also, we check the case in which we try to add a character that is already in the panel*/
    BonusPanel.addCharacter(Emma)
    assertEquals(BonusPanel.characters, characters)
  }
  test("We can also delete Characters from a BonusPanel"){
    /*Testing the case in which we are trying to remove a character from a panel without characters*/
    BonusPanel.removeCharacter(Nini, BonusPanel.characters)
    assertEquals(BonusPanel.characters, Empty)
    /*Testing the normal case*/
    BonusPanel.addCharacter(Nini)
    BonusPanel.addCharacter(Emma)
    BonusPanel.removeCharacter(Emma, BonusPanel.characters)
    characters-=Emma
    assertEquals(BonusPanel.characters,characters)
  }
  test("Bonus Panel must reward the player that lands on it with stars"){
    BonusPanel.addCharacter(Nini)
    val StarsBefore = Nini.CurrentStars
    BonusPanel.GiveStars()
    assert(Nini.CurrentStars > StarsBefore)
  }
}

