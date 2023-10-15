package cl.uchile.dcc.citric
package model.UnitsTests.WildUnitsTests

import model.Panels.PanelTypesClasses.EncounterPanel
import model.Units.WildUnits.Chicken
import munit.FunSuite
import scala.util.Random

class ChickenTest extends FunSuite {
  private val panel: EncounterPanel = new EncounterPanel()
  /*The object under test:*/
  private var chicken:Chicken =_
  override def beforeEach(context: BeforeEach): Unit = {
    chicken = new Chicken(panel,new Random(11))
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
  // 1. Test invariant properties, e.g. the result is always between 1 and 6.
  test("A Chicken should be able to roll a dice") {
    for (_ <- 1 to 10) {
      assert(chicken.rollDice() >= 1 && chicken.rollDice() <= 6)
    }
  }

  // 2. Set a seed and test the result is always the same.
  // A seed sets a fixed succession of random numbers, so you can know that the next numbers
  // are always the same for the same seed.
  test("A Chicken should be able to roll a dice with a fixed seed") {
    val other =
      new Chicken(new EncounterPanel(), new Random(11))
    for (_ <- 1 to 10) {
      assertEquals(chicken.rollDice(), other.rollDice())
    }
  }
  test("A chicken that isn't K.O should be able to do an attack") {
    /*if the chicken is K.O, it shouldn't attack*/
    chicken.CurrentHP = 0
    chicken.Attack()
    assertEquals(chicken.Attack_Quantity, 0)
    /*if it isn't K.O:*/
    /*The Attack Quantity is set as 0, so after invoking the method, it has to be >= zero*/
    chicken.CurrentHP = 3
    chicken.Attack()
    assert(chicken.Attack_Quantity >= 0 && chicken.Attack_Quantity > chicken.ATK)
  }
  test("A chicken should be able to defend itself") {
    val HP_before: Int = chicken.CurrentHP
    chicken.Defense(12)
    /*After invoking the method, the Current HP of the chicken has to be at least one unit less than the value before*/
    assert(chicken.CurrentHP < HP_before)
    /*if the HP was already equal to zero, it stays the same*/
    chicken.CurrentHP = 0
    chicken.Defense(12)
    assert(chicken.CurrentHP == 0)
  }
  test("A character should be able to evade an attack") {
    val HP_before: Int = chicken.CurrentHP
    chicken.Evasion(12)
    /*After invoking the method, the chicken receives damage equal to 0 or the same quantity of the attack*/
    assert(chicken.CurrentHP == HP_before || chicken.CurrentHP < HP_before)
    /*If it's HP is already equal to zero, it stays the same*/
    chicken.CurrentHP = 0
    chicken.Evasion(12)
    assert(chicken.CurrentHP == 0)
  }
}
