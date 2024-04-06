package cl.uchile.dcc.citric
package model.unitstests.wildunitstest

import model.panels.paneltypes.EncounterPanel
import model.units.wildunits.{Chicken, RoboBall, Seagull}

import cl.uchile.dcc.citric.model.units.players.PlayerCharacter
import cl.uchile.dcc.citric.model.units.traitunits.WildUnit
import munit.FunSuite

import scala.util.Random

class SeagullTest extends FunSuite {
  private val panel: EncounterPanel = new EncounterPanel()
  private val enemy2: Seagull = new Seagull(new EncounterPanel, new Random(11))
  private val enemy3: Chicken = new Chicken(new EncounterPanel, new Random(11))
  private val enemy4: RoboBall = new RoboBall(new EncounterPanel, new Random(11))
  private var enemy: PlayerCharacter =_
  /*The object under test:*/
  private var seagull: Seagull = _

  override def beforeEach(context: BeforeEach): Unit = {
    seagull = new Seagull(panel, new Random(11))
    enemy = new PlayerCharacter("Ammy", 10, 5, 2, 1)

  }

  test("A seagull is created with a specified Encounter Panel") {
    assertEquals(seagull.EncounterPanel, panel)
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
    assertEquals(seagull.maxHP, 3)
  }
  test("A seagull has 2 Bonus Stars that then it will give when it loses ") {
    assertEquals(seagull.BonusStars, 2)
  }
  test("A seagull starts with 0 stars and a current HP equal to the maximum HP") {
    assertEquals(seagull.CurrentStars, 0)
    assertEquals(seagull.CurrentHP, seagull.maxHP)
  }
  test("We can change the current amount of stars of a Seagull(for future methods)") {
    seagull.addStars( 3)
    assertEquals(seagull.CurrentStars, 3)
  }
  test("We can change the current HP of a Seagull(for future methods)") {
    seagull.removeHP( 2)
    assertEquals(seagull.CurrentHP, 1)
  }
  // 1. Test invariant properties, e.g. the result is always between 1 and 6.
  test("A Seagull should be able to roll a dice") {
    for (_ <- 1 to 10) {
      assert(seagull.rollDice() >= 1 && seagull.rollDice() <= 6)
    }
  }

  // 2. Set a seed and test the result is always the same.
  // A seed sets a fixed succession of random numbers, so you can know that the next numbers
  // are always the same for the same seed.
  test("A Seagull should be able to roll a dice with a fixed seed") {
    val other =
      new Seagull(new EncounterPanel(), new Random(11))
    for (_ <- 1 to 10) {
      assertEquals(seagull.rollDice(), other.rollDice())
    }
  }
  test("The amount of HP can't be negative and using negative numbers doesn't work") {
    seagull.removeHP(12)
    assertEquals(seagull.CurrentHP, 0)
    seagull.addHP(-3)
    assertEquals(seagull.CurrentHP, 0)
    seagull.removeHP(-1)
    assertEquals(seagull.CurrentHP, 0)
    seagull.addHP(2)
    seagull.addHP(-2)
    assertEquals(seagull.CurrentHP, 2)
  }
  test("The amount of Stars can't be negative and using negative numbers doesn't work") {
    seagull.removeStars(3) /*starts with zero*/
    assertEquals(seagull.CurrentStars, 0)
    seagull.addStars(-3)
    assertEquals(seagull.CurrentStars, 0)
    seagull.removeStars(-1)
    assertEquals(seagull.CurrentStars, 0)
    seagull.addStars(2)
    seagull.addStars(-2)
    assertEquals(seagull.CurrentStars, 2)
  }
  test("A Seagull should be able to do an attack to a character") {
    /*if the seagull has 0 hp, it shouldn't attack*/
    seagull.removeHP(3)
    seagull.Attack(enemy)
    assertEquals(seagull.Attack_Quantity, 0)
    /*if it isn't 0 hp:*/
    /*The Attack Quantity is set as 0, so after invoking the method, it has to be >= zero*/
    seagull.addHP(3)
    enemy.defendOrEvade = false
    seagull.Attack(enemy)
    assert(seagull.Attack_Quantity >= 0 && seagull.Attack_Quantity > seagull.ATK)
    /*if the enemy has HP 0, it shouldn't attack*/
    enemy.KO = true
    seagull.Attack(enemy)
    assertEquals(seagull.Attack_Quantity, 0)

  }
  test("A seagull wins stars when it defeats a character, the character loses them") {
    enemy.defendOrEvade = false
    enemy.addStars(3)
    seagull.Attack_Quantity = 50
    enemy.AttackWildUnit(seagull)
    assertEquals(seagull.CurrentStars, 1)
    assertEquals(enemy.CurrentStars, 2)
  }
  test("A seagull should be able to defend itself") {
    val HP_before: Int = seagull.CurrentHP
    seagull.Defense(12)
    /*After invoking the method, the Current HP of the seagull has to be at least one unit less than the value before*/
    assert(seagull.CurrentHP < HP_before && seagull.CurrentHP>=0)
    /*if the HP was already equal to zero, it stays the same*/
    val hp: Int = seagull.CurrentHP
    seagull.removeHP(hp)
    seagull.Defense(12)
    assert(seagull.CurrentHP == 0)
  }
  test("A seagull should be able to evade an attack") {
    val HP_before: Int = seagull.CurrentHP
    seagull.Evasion(12)
    /*After invoking the method, the seagull receives damage equal to 0 or the same quantity of the attack*/
    assert(seagull.CurrentHP == HP_before || seagull.CurrentHP < HP_before)
    assert(seagull.CurrentHP>=0)
    /*If it's HP is already equal to zero, it stays the same*/
    val hp: Int = seagull.CurrentHP
    seagull.removeHP(hp)
    seagull.Evasion(12)
    assert(seagull.CurrentHP == 0)
  }
  test("when a RoboBall dies, a new one is generated in the same panel") {
    val encounterPanel2: EncounterPanel = new EncounterPanel()
    assert(encounterPanel2.wildUnit(1).isInstanceOf[Seagull])
    val sea: WildUnit = encounterPanel2.wildUnit(1)
    enemy.Attack_Quantity = 100
    encounterPanel2.wildUnit(1).AttackPlayer(enemy)
    assert(sea.CurrentHP == 0)
    assert(encounterPanel2.wildUnit(2).isInstanceOf[Seagull])
    assert(encounterPanel2.wildUnit(1) != sea)
  }
}