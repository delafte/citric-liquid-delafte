package cl.uchile.dcc.citric
package model.panelstests
import model.panels.paneltypes.{BonusPanel, NeutralPanel,EncounterPanel,DropPanel, HomePanel}
import model.panels.`trait`.Panel
import model.units.players.PlayerCharacter

import munit.FunSuite

import scala.collection.mutable.ArrayBuffer
class BonusPanelTest extends FunSuite {
  /*
  * Here we define the values and variables that are necessary for testing our
  * BonusPanel class.
  */

  private val Nini: PlayerCharacter = new PlayerCharacter("Nini", 10, 5, 2, 1)
  private val Emma: PlayerCharacter = new PlayerCharacter("Emma", 15,2,5,8)
  private val NextPanels: List[Panel] = List(new EncounterPanel(),new NeutralPanel(), new DropPanel(), new BonusPanel,new HomePanel(Nini))
  private val characters: ArrayBuffer[PlayerCharacter]=ArrayBuffer(Nini,Emma)
  private val Empty: List[PlayerCharacter] = List()
  private val Empty2: List[Panel] = List()

  private var bonusPanel: BonusPanel = _
  override def beforeEach(context: BeforeEach): Unit = {
    bonusPanel = new BonusPanel()
  }
  test("A Bonus Panel is connected directly to other panels, we try all possible types"){
    bonusPanel.addPanel(NextPanels.head)
    bonusPanel.addPanel(NextPanels(1))
    bonusPanel.addPanel(NextPanels(2))
    bonusPanel.addPanel(NextPanels(3))
    bonusPanel.addPanel(NextPanels(4))
    assertEquals(bonusPanel.nextPanels, NextPanels)
  }
  test("We can remove panels connected directly to the panel") {
    bonusPanel.addPanel(NextPanels.head)
    bonusPanel.addPanel(NextPanels(1))
    bonusPanel.addPanel(NextPanels(2))
    bonusPanel.addPanel(NextPanels(3))
    bonusPanel.addPanel(NextPanels(4))
    bonusPanel.removePanel(NextPanels.head)
    bonusPanel.removePanel(NextPanels(1))
    bonusPanel.removePanel(NextPanels(2))
    bonusPanel.removePanel(NextPanels(3))
    bonusPanel.removePanel(NextPanels(4))
    assertEquals(bonusPanel.nextPanels, Empty2)
    /*If we try to remove a panel when the nextPanels is empty, it should stay the same*/
    bonusPanel.removePanel(new BonusPanel())
    assertEquals(bonusPanel.nextPanels, Empty2)
  }
  test("We can add characters to a BonusPanel"){
    bonusPanel.addCharacter(Nini)
    bonusPanel.addCharacter(Emma)
    assertEquals(bonusPanel.characters, characters.toList)
    /*Also, we check the case in which we try to add a character that is already in the panel*/
    bonusPanel.addCharacter(Emma)
    assertEquals(bonusPanel.characters, characters.toList)
  }
  test("We can also delete Characters from a BonusPanel"){
    /*Testing the case in which we are trying to remove a character from a panel without characters*/
    bonusPanel.removeCharacter(Nini)
    assertEquals(bonusPanel.characters, Empty)
    /*Testing the normal case*/
    bonusPanel.addCharacter(Nini)
    bonusPanel.addCharacter(Emma)
    bonusPanel.removeCharacter(Emma)
    characters-=Emma
    assertEquals(bonusPanel.characters,characters.toList)
    /*Testing the case in which we try to delete a character that isn't in the array*/
    bonusPanel.removeCharacter(Emma)
    assertEquals(bonusPanel.characters, characters.toList)
  }
  test("Bonus Panel must reward the players that lands on it with stars"){
    bonusPanel.addCharacter(Nini)
    val StarsBefore = Nini.CurrentStars
    val StarsBefore2 = Emma.CurrentStars
    bonusPanel.apply(Nini)
    bonusPanel.apply(Emma)
    assert(Nini.CurrentStars > StarsBefore)
    assert(Emma.CurrentStars == StarsBefore2)
  }
}

