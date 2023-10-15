package cl.uchile.dcc.citric
package model.UnitsTests.WildUnitsTests

import model.Panels.PanelTypesClasses.EncounterPanel
import model.Units.WildUnits.RoboBall
import munit.FunSuite
import scala.util.Random

class RoboBallTest extends FunSuite {
  private val panel: EncounterPanel = new EncounterPanel()
  /*The object under test:*/
  private var roboball: RoboBall =_

  override def beforeEach(context: BeforeEach): Unit = {
    roboball = new RoboBall(panel, new Random(11))
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
  // 1. Test invariant properties, e.g. the result is always between 1 and 6.
  test("A RoboBall should be able to roll a dice") {
    for (_ <- 1 to 10) {
      assert(roboball.rollDice() >= 1 && roboball.rollDice() <= 6)
    }
  }

  // 2. Set a seed and test the result is always the same.
  // A seed sets a fixed succession of random numbers, so you can know that the next numbers
  // are always the same for the same seed.
  test("A Roboball should be able to roll a dice with a fixed seed") {
    val other =
      new RoboBall(new EncounterPanel(), new Random(11))
    for (_ <- 1 to 10) {
      assertEquals(roboball.rollDice(), other.rollDice())
    }
  }
  test("A RoboBall should be able to do an attack") {
    /*if the roboBall is K.O, it shouldn't attack*/
    roboball.CurrentHP = 0
    roboball.Attack()
    assertEquals(roboball.Attack_Quantity, 0)
    /*if it isn't K.O:*/
    /*The Attack Quantity is set as 0, so after invoking the method, it has to be >= zero*/
    roboball.CurrentHP = 3
    roboball.Attack()
    assert(roboball.Attack_Quantity >= 0 && roboball.Attack_Quantity > roboball.ATK)
  }
  test("A roboball should be able to defend itself") {
    val HP_before: Int = roboball.CurrentHP
    roboball.Defense(12)
    /*After invoking the method, the Current HP of the roboball has to be at least one unit less than the value before*/
    assert(roboball.CurrentHP < HP_before)
    /*if the HP was already equal to zero, it stays the same*/
    roboball.CurrentHP = 0
    roboball.Defense(12)
    assert(roboball.CurrentHP == 0)
  }
  test("A roboball should be able to evade an attack") {
    val HP_before: Int = roboball.CurrentHP
    roboball.Evasion(12)
    /*After invoking the method, the roboball receives damage equal to 0 or the same quantity of the attack*/
    assert(roboball.CurrentHP == HP_before || roboball.CurrentHP < HP_before)
    /*If it's HP is already equal to zero, it stays the same*/
    roboball.CurrentHP = 0
    roboball.Evasion(12)
    assert(roboball.CurrentHP == 0)
  }
}
