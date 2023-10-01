package cl.uchile.dcc.citric
package model.UnitsTests.WildUnitsTests

import model.Panels.PanelTypesClasses.EncounterPanel
import model.Units.WildUnits.Seagull
import munit.FunSuite

class SeagullTest extends FunSuite {
  private val panel: EncounterPanel = new EncounterPanel()
  /*The object under test:*/
  private var seagull: Seagull = _

  override def beforeEach(context: BeforeEach): Unit = {
    seagull = new Seagull(panel)
  }

  test("A seagull is created with a specified Encounter Panel") {
    assertEquals(seagull.Panel, panel)
  }
  test("A seagull has atk = 1") {
    assertEquals(seagull.ATK, 1)
  }
  test("A seagull has def = -1") {
    assertEquals(seagull.DEF, -1)
  }
  test("A seagull has evasion = -1") {
    assertEquals(seagull.EVA, -1)
  }
  test("A chicken has maxHP = 3") {
    assertEquals(seagull.MaxHP, 3)
  }
  test("A seagull starts with 0 stars and a current HP equal to the maximum HP") {
    assertEquals(seagull.CurrentStars, 0)
    assertEquals(seagull.CurrentHP, seagull.MaxHP)
  }
  test("We can change the current amount of stars of a Seagull(for future methods)") {
    seagull.CurrentStars = 3
    assertEquals(seagull.CurrentStars, 3)
  }
  test("We can change the current HP of a Seagull(for future methods)") {
    seagull.CurrentHP = 1
    assertEquals(seagull.CurrentHP, 1)
  }
}