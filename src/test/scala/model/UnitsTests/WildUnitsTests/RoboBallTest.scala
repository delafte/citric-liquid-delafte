package cl.uchile.dcc.citric
package model.UnitsTests.WildUnitsTests

import cl.uchile.dcc.citric.model.Units.WildUnits.RoboBall
import munit.FunSuite

class RoboBallTest extends FunSuite {
  /*The object under test:*/
  private val roboball: RoboBall = new RoboBall()

  test("A roboball has atk = -1"){
    assertEquals(roboball.ATK, -1)
  }
  test("A roboball has def = 1"){
    assertEquals(roboball.DEF, 1)
  }
  test("A roboball has evasion = -1"){
    assertEquals(roboball.EVA, -1)
  }
  test("A roboball has maxHP = 3"){
    assertEquals(roboball.maxHP, 3)
  }
}