package cl.uchile.dcc.citric
package model.UnitsTests.WildUnitsTests

import model.Panels.PanelTypesClasses.EncounterPanel
import model.Units.WildUnits.RoboBall
import munit.FunSuite

class RoboBallTest extends FunSuite {
  private val panel: EncounterPanel = new EncounterPanel()
  /*The object under test:*/
  private var roboball: RoboBall =_

  override def beforeEach(context: BeforeEach): Unit = {
    roboball = new RoboBall(panel)
  }
  test("A RoboBall is created with a specified Encounter Panel") {
    assertEquals(roboball.Panel, panel)
  }
  test("A RoboBall has atk = -1"){
    assertEquals(roboball.ATK, -1)
  }
  test("A RoboBall has def = 1"){
    assertEquals(roboball.DEF, 1)
  }
  test("A RoboBall has evasion = -1"){
    assertEquals(roboball.EVA, -1)
  }
  test("A RoboBall has maxHP = 3"){
    assertEquals(roboball.MaxHP, 3)
  }
  test("A RoboBall starts with 0 stars and a current HP equal to the maximum HP") {
    assertEquals(roboball.CurrentStars, 0)
    assertEquals(roboball.CurrentHP, roboball.MaxHP)
  }
  test("We can change the current amount of stars of a RoboBall(for future methods)") {
    roboball.CurrentStars = 3
    assertEquals(roboball.CurrentStars, 3)
  }
  test("We can change the current HP of a RoboBall(for future methods)") {
    roboball.CurrentHP = 1
    assertEquals(roboball.CurrentHP, 1)
  }
}