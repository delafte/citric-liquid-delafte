package cl.uchile.dcc.citric
package model.unitstests.wildunitstest

import model.panels.paneltypes.EncounterPanel
import model.units.wildunits.Chicken

import cl.uchile.dcc.citric.model.units.players.PlayerCharacter
import munit.FunSuite

import scala.util.Random

class ChickenTest extends FunSuite {
  private val panel: EncounterPanel = new EncounterPanel()

  private var enemy: PlayerCharacter =_
  /*The object under test:*/
  private var chicken:Chicken =_
  override def beforeEach(context: BeforeEach): Unit = {
    chicken = new Chicken(panel,new Random(11))
    enemy =new PlayerCharacter("Ammy", 10, 5, 2, 1)

  }

  test("A chicken is created with a specified Encounter Panel"){
    assertEquals(chicken.EncounterPanel, panel)
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
    assertEquals(chicken.maxHP, 3)
  }
  test("A chicken has 3 Bonus Stars that then it will give when it loses ") {
    assertEquals(chicken.BonusStars, 3)
  }
  test("A chicken starts with 0 stars and a current HP equal to the maximum HP") {
    assertEquals(chicken.CurrentStars, 0)
    assertEquals(chicken.CurrentHP, chicken.maxHP)
  }
  test("We can change the current amount of stars of a chicken(for future methods)"){
    chicken.addStars(3)
    assertEquals(chicken.CurrentStars, 3)
  }
  test("We can change the current HP of a chicken(for future methods)"){
    chicken.removeHP(2)
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
  test("The amount of HP can't be negative and using negative numbers doesn't work") {
    chicken.removeHP(12)
    assertEquals(chicken.CurrentHP, 0)
    chicken.addHP(-3)
    assertEquals(chicken.CurrentHP, 0)
    chicken.removeHP(-1)
    assertEquals(chicken.CurrentHP, 0)
    chicken.addHP(2)
    chicken.addHP(-2)
    assertEquals(chicken.CurrentHP, 2)
    chicken.addHP(0)
    assertEquals(chicken.CurrentHP, 2)
  }
  test("The amount of Stars can't be negative and using negative numbers doesn't work") {
    chicken.removeStars(3) /*starts with zero*/
    assertEquals(chicken.CurrentStars, 0)
    chicken.addStars(-3)
    assertEquals(chicken.CurrentStars, 0)
    chicken.removeStars(-1)
    assertEquals(chicken.CurrentStars, 0)
    chicken.addStars(2)
    chicken.addStars(-2)
    assertEquals(chicken.CurrentStars, 2)
    chicken.addStars(0)
    assertEquals(chicken.CurrentStars,2)
  }
  test("A chicken should be able to do an attack") {
    /*if the chicken has 0 Hp, it shouldn't attack*/
    chicken.removeHP(3)
    chicken.Attack(enemy)
    assertEquals(chicken.Attack_Quantity, 0)
    /*if it isn't 0 hp:*/
    /*The Attack Quantity is set as 0, so after invoking the method, it has to be >= zero*/
    chicken.addHP(3)
    enemy.defendOrEvade=false
    chicken.Attack(enemy)
    assert(chicken.Attack_Quantity >= 0 && chicken.Attack_Quantity > chicken.ATK)
    /*if the enemy is KO, it shouldn't attack*/
    enemy.KO = true
    chicken.Attack(enemy)
    assertEquals(chicken.Attack_Quantity, 0)
  }
  test("A chicken wins stars when it defeats a character, the character loses them"){
    enemy.defendOrEvade = false
    enemy.addStars(3)
    chicken.Attack_Quantity = 50
    enemy.AttackWildUnit(chicken)
    assertEquals(chicken.CurrentStars, 1)
    assertEquals(enemy.CurrentStars, 2)
  }
  test("A chicken should be able to defend itself") {
    val HP_before: Int = chicken.CurrentHP
    chicken.Defense(12)
    /*After invoking the method, the Current HP of the chicken has to be at least one unit less than the value before*/
    assert(chicken.CurrentHP < HP_before && chicken.CurrentHP>=0)
    /*if the HP was already equal to zero, it stays the same*/
    val hp: Int = chicken.CurrentHP
    chicken.removeHP(hp)
    chicken.Defense(12)
    assert(chicken.CurrentHP == 0)
  }
  test("A chicken should be able to evade an attack") {
    val HP_before: Int = chicken.CurrentHP
    chicken.Evasion(12)
    /*After invoking the method, the chicken receives damage equal to 0 or the same quantity of the attack*/
    assert(chicken.CurrentHP == HP_before || chicken.CurrentHP < HP_before)
    assert(chicken.CurrentHP>=0)
    /*If it's HP is already equal to zero, it stays the same*/
    val hp: Int = chicken.CurrentHP
    chicken.removeHP(hp)
    chicken.Evasion(12)
    assert(chicken.CurrentHP == 0)
  }
}
