package cl.uchile.dcc.citric
package model.panelstests

import model.panels.paneltypes.{BonusPanel, DropPanel, EncounterPanel, HomePanel, NeutralPanel}
import model.panels.`trait`.Panel
import model.units.players.PlayerCharacter
import model.norm.{Norm1, Norm2, Norm3, Norm4, Norm5, Norm6}

import cl.uchile.dcc.citric.model.controlador.GameController
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
  private val Empty2: List[Panel] = List()
  private val context: GameController= new GameController()


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
    assertEquals(homePanel.nextPanels, NextPanels.toList)
  }
  test("We can remove panels connected directly to the panel") {
    homePanel.addPanel(NextPanels.head)
    homePanel.addPanel(NextPanels(1))
    homePanel.addPanel(NextPanels(2))
    homePanel.addPanel(NextPanels(3))
    homePanel.addPanel(NextPanels(4))
    homePanel.removePanel(NextPanels.head)
    homePanel.removePanel(NextPanels(1))
    homePanel.removePanel(NextPanels(2))
    homePanel.removePanel(NextPanels(3))
    homePanel.removePanel(NextPanels(4))
    assertEquals(homePanel.nextPanels, Empty2)
    /*If we try to remove a panel when the nextPanels is empty, it should stay the same*/
    homePanel.removePanel(new BonusPanel())
    assertEquals(homePanel.nextPanels, Empty2)
  }
  test("We can add characters to a HomePanel"){
    homePanel.addCharacter(Nini)
    homePanel.addCharacter(Emma)
    assertEquals(homePanel.characters, characters.toList)
    /*Also, we check the case in which we try to add a character that is already in the panel*/
    homePanel.addCharacter(Emma)
    assertEquals(homePanel.characters, characters.toList)
  }
  test("We can also delete Characters from a HomePanel"){
    /*Testing the case in which we are trying to remove a character from a panel without characters*/
    homePanel.removeCharacter(Nini)
    assertEquals(homePanel.characters,Empty.toList)
    /*Testing the normal situation*/
    homePanel.addCharacter(Nini)
    homePanel.addCharacter(Emma)
    homePanel.removeCharacter(Emma)
    characters -= Emma
    assertEquals(homePanel.characters,characters.toList)
    /*Testing the case in which we try to delete a character that isn't in the array*/
    homePanel.removeCharacter(Emma)
    assertEquals(homePanel.characters,characters.toList)
  }
  test("The HomePanel has the NormaCheck effect and it affects the characters that land on the panel"){
    homePanel.addCharacter(Nini)
    Nini.Obj_stars = true
    Nini.addStars(11)
    assert(Nini.CurrentNorm.isInstanceOf[Norm1])
    homePanel.NormaCheck(Nini)
    assert(Nini.CurrentNorm.isInstanceOf[Norm2])

    /*if the character doesn't have the requirements to upgrade*/
    Nini.Obj_stars = true
    Nini.CurrentNorm = new Norm1
    Nini.removeStars(9)
    assert(Nini.CurrentNorm.isInstanceOf[Norm1])
    homePanel.NormaCheck(Nini)
    assert(Nini.CurrentNorm.isInstanceOf[Norm1])/*stays the same*/
  }
  test("The home panel has the apply effect that heals 1 point the player and makes normCheck"){
    homePanel.addCharacter(Nini)
    Nini.Obj_stars = true
    Nini.removeHP(1)
    Nini.addStars(11)
    assert(Nini.CurrentNorm.isInstanceOf[Norm1])
    homePanel.apply(Nini,context)
    assert(Nini.CurrentNorm.isInstanceOf[Norm2])
    assert(Nini.CurrentHP == Nini.maxHP)
  }
}
