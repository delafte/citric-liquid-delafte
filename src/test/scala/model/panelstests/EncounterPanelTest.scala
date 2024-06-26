package cl.uchile.dcc.citric
package model.panelstests

import model.panels.paneltypes.{BonusPanel, DropPanel, EncounterPanel, HomePanel, NeutralPanel}
import model.panels.`trait`.Panel
import model.units.players.PlayerCharacter

import cl.uchile.dcc.citric.model.controller.GameController
import cl.uchile.dcc.citric.model.units.traitunits.WildUnit
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
  private val Empty2: List[Panel] = List()
  private val context: GameController= new GameController()


  private var encounterPanel: EncounterPanel = _
  override def beforeEach(context: BeforeEach): Unit = {
    encounterPanel = new EncounterPanel()
  }
  test("A random wild unit is selected in the panel"){
    val selected = encounterPanel.randomWildUnit()
    assert(selected.isInstanceOf[WildUnit])
  }
  test("When a character lands on a panel, a combat is initialized with the wild unit"){
    context.gameStarts()
    context.startTurnPlayer()
    context.rollD()
    context.outOfMoves()
    context.decideNotFightCharacter()
    encounterPanel.addCharacter(Ammy)
    encounterPanel.apply(Ammy,context)
    assert(context.inCombat())
  }
  test("An Encounter Panel is connected directly to other panels, we try all possible types") {
    encounterPanel.addPanel(NextPanels(0))
    encounterPanel.addPanel(NextPanels(1))
    encounterPanel.addPanel(NextPanels(2))
    encounterPanel.addPanel(NextPanels(3))
    encounterPanel.addPanel(NextPanels(4))
    assertEquals(encounterPanel.nextPanels, NextPanels.toList)
  }
  test("We can remove panels connected directly to the panel") {
    encounterPanel.addPanel(NextPanels.head)
    encounterPanel.addPanel(NextPanels(1))
    encounterPanel.addPanel(NextPanels(2))
    encounterPanel.addPanel(NextPanels(3))
    encounterPanel.addPanel(NextPanels(4))
    encounterPanel.removePanel(NextPanels.head)
    encounterPanel.removePanel(NextPanels(1))
    encounterPanel.removePanel(NextPanels(2))
    encounterPanel.removePanel(NextPanels(3))
    encounterPanel.removePanel(NextPanels(4))
    assertEquals(encounterPanel.nextPanels, Empty2)
    /*If we try to remove a panel when the nextPanels is empty, it should stay the same*/
    encounterPanel.removePanel(new BonusPanel())
    assertEquals(encounterPanel.nextPanels, Empty2)
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
  test("we can remove wildUnits from the encounter panel"){
    assert(encounterPanel.wildUnit.length==3)
    encounterPanel.remove(encounterPanel.wildUnit(0))
    assert(encounterPanel.wildUnit.length == 2)
  }

}

