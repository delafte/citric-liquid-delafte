package cl.uchile.dcc.citric
package model.UnitsTests.WildUnitsTests

import cl.uchile.dcc.citric.model.Units.WildUnits.Seagull
import munit.FunSuite

class SeagullTest extends FunSuite {
  /*The object under test:*/
  private val seagull: Seagull = new Seagull()

  test("A seagull has atk = 1"){
    assertEquals(seagull.ATK, 1)
  }
  test("A seagull has def = -1"){
    assertEquals(seagull.DEF, -1)
  }
  test("A seagull has evasion = -1"){
    assertEquals(seagull.EVA, -1)
  }
  test("A chicken has maxHP = 3"){
    assertEquals(seagull.maxHP, 3)
  }
}