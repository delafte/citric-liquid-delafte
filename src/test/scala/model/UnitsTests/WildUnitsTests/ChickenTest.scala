package cl.uchile.dcc.citric
package model.UnitsTests.WildUnitsTests

import cl.uchile.dcc.citric.model.Units.WildUnits.Chicken
import munit.FunSuite

class ChickenTest extends FunSuite {
  /*The object under test:*/
  private val chicken: Chicken = new Chicken()

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
    assertEquals(chicken.maxHP, 3)
  }
}
