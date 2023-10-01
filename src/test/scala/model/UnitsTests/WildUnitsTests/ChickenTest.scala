package cl.uchile.dcc.citric
package model.UnitsTests.WildUnitsTests

import model.Panels.PanelTypesClasses.EncounterPanel
import model.Units.WildUnits.Chicken
import munit.FunSuite

class ChickenTest extends FunSuite {
  private val panel: EncounterPanel = new EncounterPanel()
  /*The object under test:*/
  private var chicken:Chicken =_
  override def beforeEach(context: BeforeEach): Unit = {
    chicken = new Chicken(panel)
  }

  test("A chicken is created with a specified Encounter Panel"){
    assertEquals(chicken.Panel, panel)
  }
  test("A chicken has atk = -1"){
    assertEquals(chicken.ATK, -1)
  }
  test("A chicken has def = -1"){
    assertEquals(chicken.DEF, -1)
  }
  test("A chicken has evasion = 1"){
    assertEquals(chicken.EVA, 1)
  }
  test("A chicken has maxHP = 3"){
    assertEquals(chicken.MaxHP, 3)
  }
  test("A chicken starts with 0 stars and a current HP equal to the maximum HP") {
    assertEquals(chicken.CurrentStars, 0)
    assertEquals(chicken.CurrentHP, chicken.MaxHP)
  }
  test("We can change the current amount of stars of a chicken(for future methods)"){
    chicken.CurrentStars = 3
    assertEquals(chicken.CurrentStars, 3)
  }
  test("We can change the current HP of a chicken(for future methods)"){
    chicken.CurrentHP=1
    assertEquals(chicken.CurrentHP, 1)
  }
}
