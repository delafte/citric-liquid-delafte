package cl.uchile.dcc.citric
package model.UnitsTests.WildUnitsTests

import model.Panels.PanelTypesClasses.EncounterPanel
import model.Units.WildUnits.RoboBall
import munit.FunSuite

class RoboBallTest extends FunSuite {
  /*The object under test:*/
  private val panel: EncounterPanel = new EncounterPanel()
  private val roboball: RoboBall = new RoboBall(panel)
  test("A RoboBall is created with a specified Encounter Panel") {
    assertEquals(roboball.panel, panel)
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
    assertEquals(roboball.maxHP, 3)
  }
  test("A RoboBall starts with 0 stars and a current HP equal to the maximum HP") {
    assertEquals(roboball.CurrentStars, 0)
    assertEquals(roboball.CurrentHP, roboball.maxHP)
  }
}