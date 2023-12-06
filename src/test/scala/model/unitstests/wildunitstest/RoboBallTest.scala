package cl.uchile.dcc.citric
package model.unitstests.wildunitstest

import model.panels.paneltypes.EncounterPanel
import model.units.wildunits.{Chicken, RoboBall, Seagull}

import cl.uchile.dcc.citric.model.units.players.PlayerCharacter
import cl.uchile.dcc.citric.model.units.traitunits.WildUnit
import munit.FunSuite

import scala.util.Random

class RoboBallTest extends FunSuite {
  private val panel: EncounterPanel = new EncounterPanel()
  private var enemy: PlayerCharacter =_
  /*The object under test:*/
  private var roboball: RoboBall =_

  override def beforeEach(context: BeforeEach): Unit = {
    roboball = new RoboBall(panel,new Random(11))
    enemy =new PlayerCharacter("Ammy", 10, 5, 2, 1)

  }
  test("A RoboBall is created with a specified Encounter Panel") {
    assertEquals(roboball.EncounterPanel, panel)
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
  test("A RoboBall has 2 Bonus Stars that then it will give when it loses ") {
    assertEquals(roboball.BonusStars, 2)
  }
  test("A RoboBall starts with 0 stars and a current HP equal to the maximum HP") {
    assertEquals(roboball.CurrentStars, 0)
    assertEquals(roboball.CurrentHP, roboball.maxHP)
  }
  test("We can change the current amount of stars of a RoboBall(for future methods)") {
    roboball.addStars( 3)
    assertEquals(roboball.CurrentStars, 3)
  }
  test("We can change the current HP of a RoboBall(for future methods)") {
    roboball.removeHP(2)
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
  test("The amount of HP can't be negative and using negative numbers doesn't work") {
    roboball.removeHP(12)
    assertEquals(roboball.CurrentHP, 0)
    roboball.addHP(-3)
    assertEquals(roboball.CurrentHP, 0)
    roboball.removeHP(-1)
    assertEquals(roboball.CurrentHP, 0)
    roboball.addHP(2)
    roboball.addHP(-2)
    assertEquals(roboball.CurrentHP, 2)
  }
  test("The amount of Stars can't be negative and using negative numbers doesn't work") {
    roboball.removeStars(3) /*starts with zero*/
    assertEquals(roboball.CurrentStars, 0)
    roboball.addStars(-3)
    assertEquals(roboball.CurrentStars, 0)
    roboball.removeStars(-1)
    assertEquals(roboball.CurrentStars, 0)
    roboball.addStars(2)
    roboball.addStars(-2)
    assertEquals(roboball.CurrentStars, 2)
  }
  test("A RoboBall should be able to do an attack to a character only") {
    /*if the roboBall has 0 hp, it shouldn't attack*/
    roboball.removeHP(3)
    roboball.Attack(enemy)
    assertEquals(roboball.Attack_Quantity, 0)
    /*if it isn't 0 hp:*/
    /*The Attack Quantity is set as 0, so after invoking the method, it has to be >= zero*/
    roboball.addHP(3)
    enemy.defendOrEvade=false
    roboball.Attack(enemy)
    assert(roboball.Attack_Quantity >= 0 && roboball.Attack_Quantity > roboball.ATK)
    /*if the enemy has HP 0, it shouldn't attack*/
    enemy.KO = true
    roboball.Attack(enemy)
    assertEquals(roboball.Attack_Quantity, 0)

  }
  test("A RoboBall wins stars when it defeats a character, the character loses them") {
    enemy.defendOrEvade = true
    enemy.addStars(3)
    roboball.Attack_Quantity = 50
    enemy.AttackWildUnit(roboball)
    assertEquals(roboball.CurrentStars, 1)
    assertEquals(enemy.CurrentStars, 2)
  }
  test("A roboball should be able to defend itself") {
    val HP_before: Int = roboball.CurrentHP
    roboball.Defense(12)
    /*After invoking the method, the Current HP of the roboball has to be at least one unit less than the value before*/
    assert(roboball.CurrentHP < HP_before && roboball.CurrentHP>=0)
    /*if the HP was already equal to zero, it stays the same*/
    val hp: Int = roboball.CurrentHP
    roboball.removeHP(hp)
    roboball.Defense(12)
    assert(roboball.CurrentHP == 0)
  }
  test("A roboball should be able to evade an attack") {
    val HP_before: Int = roboball.CurrentHP
    roboball.Evasion(12)
    /*After invoking the method, the roboball receives damage equal to 0 or the same quantity of the attack*/
    assert(roboball.CurrentHP == HP_before || roboball.CurrentHP < HP_before)
    assert(roboball.CurrentHP>=0)
    /*If it's HP is already equal to zero, it stays the same*/
    val hp: Int = roboball.CurrentHP
    roboball.removeHP(hp)
    roboball.Evasion(12)
    assert(roboball.CurrentHP == 0)
  }
  test("when a RoboBall dies, a new one is generated in the same panel") {
    val encounterPanel2: EncounterPanel = new EncounterPanel()
    assert(encounterPanel2.wildUnit(2).isInstanceOf[RoboBall])
    val rob: WildUnit = encounterPanel2.wildUnit(2)
    enemy.Attack_Quantity = 100
    encounterPanel2.wildUnit(2).AttackPlayer(enemy)
    assert(rob.CurrentHP == 0)
    assert(encounterPanel2.wildUnit(2).isInstanceOf[RoboBall])
    assert(encounterPanel2.wildUnit(2) != rob)
  }
}
