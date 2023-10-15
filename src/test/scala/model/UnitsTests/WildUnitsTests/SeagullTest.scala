package cl.uchile.dcc.citric
package model.UnitsTests.WildUnitsTests

import model.Panels.PanelTypesClasses.EncounterPanel
import model.Units.WildUnits.Seagull
import munit.FunSuite
import scala.util.Random

class SeagullTest extends FunSuite {
  private val panel: EncounterPanel = new EncounterPanel()
  /*The object under test:*/
  private var seagull: Seagull = _

  override def beforeEach(context: BeforeEach): Unit = {
    seagull = new Seagull(panel, new Random(11))
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
  // 1. Test invariant properties, e.g. the result is always between 1 and 6.
  test("A Seagull should be able to roll a dice") {
    for (_ <- 1 to 10) {
      assert(seagull.rollDice(seagull.randomNumberGenerator) >= 1 && seagull.rollDice(seagull.randomNumberGenerator) <= 6)
    }
  }

  // 2. Set a seed and test the result is always the same.
  // A seed sets a fixed succession of random numbers, so you can know that the next numbers
  // are always the same for the same seed.
  test("A Seagull should be able to roll a dice with a fixed seed") {
    val other =
      new Seagull(new EncounterPanel(), new Random(11))
    for (_ <- 1 to 10) {
      assertEquals(seagull.rollDice(seagull.randomNumberGenerator), other.rollDice(other.randomNumberGenerator))
    }
  }
  test("A Seagull should be able to do an attack") {
    /*if the seagull is K.O, it shouldn't attack*/
    seagull.CurrentHP = 0
    seagull.Attack(seagull.randomNumberGenerator)
    assertEquals(seagull.Attack_Quantity, 0)
    /*if it isn't K.O:*/
    /*The Attack Quantity is set as 0, so after invoking the method, it has to be >= zero*/
    seagull.CurrentHP = 3
    seagull.Attack(seagull.randomNumberGenerator)
    assert(seagull.Attack_Quantity >= 0 && seagull.Attack_Quantity > seagull.ATK)
  }
  test("A seagull should be able to defend itself") {
    val HP_before: Int = seagull.CurrentHP
    seagull.Defense(12, seagull.randomNumberGenerator)
    /*After invoking the method, the Current HP of the seagull has to be at least one unit less than the value before*/
    assert(seagull.CurrentHP < HP_before)
    /*if the HP was already equal to zero, it stays the same*/
    seagull.CurrentHP = 0
    seagull.Defense(12, seagull.randomNumberGenerator)
    assert(seagull.CurrentHP == 0)
  }
  test("A seagull should be able to evade an attack") {
    val HP_before: Int = seagull.CurrentHP
    seagull.Evasion(12, seagull.randomNumberGenerator)
    /*After invoking the method, the seagull receives damage equal to 0 or the same quantity of the attack*/
    assert(seagull.CurrentHP == HP_before || seagull.CurrentHP < HP_before)
    /*If it's HP is already equal to zero, it stays the same*/
    seagull.CurrentHP = 0
    seagull.Evasion(12, seagull.randomNumberGenerator)
    assert(seagull.CurrentHP == 0)
  }
}