package cl.uchile.dcc.citric
package model.panelstests

import model.panels.paneltypes.{HomePanel, NeutralPanel, BonusPanel,DropPanel,EncounterPanel}
import model.panels.`trait`.Panel
import model.units.players.PlayerCharacter
import model.norm.Norm1
import scala.util.Random

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
  private val Empty: ArrayBuffer[PlayerCharacter]=ArrayBuffer()
  private val Empty2: List[Panel] = List()

  private var characters: ArrayBuffer[PlayerCharacter]=_
  private var neutralPanel: NeutralPanel = _
  override def beforeEach(context: BeforeEach): Unit = {
    neutralPanel = new NeutralPanel()
    characters = ArrayBuffer(Nini,Emma)
  }
  test("A NeutralPanel is connected directly to other panels, we try all possible types "){
    neutralPanel.addPanel(NextPanels(0))
    neutralPanel.addPanel(NextPanels(1))
    neutralPanel.addPanel(NextPanels(2))
    neutralPanel.addPanel(NextPanels(3))
    neutralPanel.addPanel(NextPanels(4))
    assertEquals(neutralPanel.nextPanels, NextPanels.toList)
  }
  test("We can remove panels connected directly to the panel") {
    neutralPanel.addPanel(NextPanels.head)
    neutralPanel.addPanel(NextPanels(1))
    neutralPanel.addPanel(NextPanels(2))
    neutralPanel.addPanel(NextPanels(3))
    neutralPanel.addPanel(NextPanels(4))
    neutralPanel.removePanel(NextPanels.head)
    neutralPanel.removePanel(NextPanels(1))
    neutralPanel.removePanel(NextPanels(2))
    neutralPanel.removePanel(NextPanels(3))
    neutralPanel.removePanel(NextPanels(4))
    assertEquals(neutralPanel.nextPanels, Empty2)
    /*If we try to remove a panel when the nextPanels is empty, it should stay the same*/
    neutralPanel.removePanel(new BonusPanel())
    assertEquals(neutralPanel.nextPanels, Empty2)
  }
  test("We can add characters to a NeutralPanel"){
    neutralPanel.addCharacter(Nini)
    neutralPanel.addCharacter(Emma)
    assertEquals(neutralPanel.characters, characters.toList)
    /*Also, we check the case in which we try to add a character that is already in the panel*/
    neutralPanel.addCharacter(Emma)
    assertEquals(neutralPanel.characters, characters.toList)
  }
  test("We can also delete Characters from a NeutralPanel"){
    /*Testing the case in which we are trying to remove a character from a panel without characters*/
    neutralPanel.removeCharacter(Nini)
    assertEquals(neutralPanel.characters,Empty.toList)
    /*Testing the normal situation*/
    neutralPanel.addCharacter(Nini)
    neutralPanel.addCharacter(Emma)
    neutralPanel.removeCharacter(Emma)
    characters -= Emma
    assertEquals(neutralPanel.characters,characters.toList)
    /*Testing the case in which we try to delete a character that isn't in the array*/
    neutralPanel.removeCharacter(Emma)
    assertEquals(neutralPanel.characters, characters.toList)
  }
  test("The NeutralPanel has an apply method that does nothing"){
    neutralPanel.addCharacter(Nini)
    neutralPanel.addCharacter(Emma)
    neutralPanel.addPanel(NextPanels(0))
    neutralPanel.addPanel(NextPanels(1))
    neutralPanel.addPanel(NextPanels(2))
    neutralPanel.addPanel(NextPanels(3))
    neutralPanel.addPanel(NextPanels(4))
    neutralPanel.apply(Emma)
    /*Now we verify that everything stays the same*/
    assertEquals(neutralPanel.characters,characters.toList)
    assertEquals(Emma.name, "Emma")
    assertEquals(Emma.CurrentStars, 0)
    assert(Emma.CurrentNorm.isInstanceOf[Norm1])
    assertEquals(Emma.Defend, false)
    assertEquals(Emma.Evade, false)
    assertEquals(Emma.CurrentHP, Emma.maxHP)
    assertEquals(Emma.maxHP, 15)
    assertEquals(Emma.ATK, 2)
    assertEquals(Emma.DEF,5)
    assertEquals(Emma.EVA,8)
    assertEquals(Emma.Attack_Quantity, 0)
    assertEquals(Emma.KO, false)
    assertEquals(Emma.Victories, 0)
    assertEquals(neutralPanel.nextPanels, NextPanels.toList)
  }
}
