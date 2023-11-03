package cl.uchile.dcc.citric
package model.unitstests

import model.units.players.PlayerCharacter

import scala.util.Random
import model.units.wildunits.{Chicken, RoboBall,Seagull}
import model.panels.paneltypes.EncounterPanel

class PlayerCharacterTest extends munit.FunSuite {

  private val name = "testPlayer"
  private val maxHp = 10
  private val attack = 10
  private val defense = 1
  private val evasion = 1
  private val enemy: Chicken = new Chicken(new EncounterPanel, new Random(11))
  private val enemy2: RoboBall = new RoboBall(new EncounterPanel, new Random(11))
  private val enemy3: Seagull = new Seagull(new EncounterPanel, new Random(11))
  /*
  This is the object under test.
  We initialize it in the beforeEach method so we can reuse it in all the tests.
  This is a good practice because it will reset the object before each test, so you don't have
  to worry about the state of the object between tests.
  */
  private var character: PlayerCharacter = _  // <- x = _ is the same as x = null
  private var enemy4: PlayerCharacter = _
  // This method is executed before each `test(...)` method.
  override def beforeEach(context: BeforeEach): Unit = {
    val randomNumberGenerator = new Random(11)
    character = new PlayerCharacter(
      name,
      maxHp,
      attack,
      defense,
      evasion,
      randomNumberGenerator
    )
    enemy4 = new PlayerCharacter("emma", 10, 2, 0, 0, new Random(11))

  }

  test("A character should have correctly set their attributes and variables") {
    /*here we also test de getters*/
    assertEquals(character.name, name)
    assertEquals(character.maxHP, maxHp)
    assertEquals(character.ATK, attack)
    assertEquals(character.DEF, defense)
    assertEquals(character.EVA, evasion)
    assertEquals(character.CurrentStars, 0)
    assertEquals(character.Victories, 0)
    assertEquals(character.CurrentNorm.NumberNorm, 1)
    assertEquals(character.CurrentHP, maxHp)
    assertEquals(character.Evade,false)
    assertEquals(character.Defend,false)
  }

  // Two ways to test randomness (you can use any of them):

  // 1. Test invariant properties, e.g. the result is always between 1 and 6.
  test("A character should be able to roll a dice") {
    for (_ <- 1 to 10) {
      assert(character.rollDice() >= 1 && character.rollDice() <= 6)
    }
  }

  // 2. Set a seed and test the result is always the same.
  // A seed sets a fixed succession of random numbers, so you can know that the next numbers
  // are always the same for the same seed.
  test("A character should be able to roll a dice with a fixed seed") {
    val other =
      new PlayerCharacter(name, maxHp, attack, defense, evasion, new Random(11))
    for (_ <- 1 to 10) {
      assertEquals(character.rollDice(), other.rollDice())
    }
  }
  test("A character can be healed with +1 points of HP"){
    /*First, we test the case in which the character is already full Hp*/
    character.heal()
    assertEquals(character.CurrentHP,character.maxHP)
    /*Now the case in which the character isn't full hp*/
    character.CurrentHP_=(5)
    character.heal()
    assertEquals(character.CurrentHP,6)
  }
  test("The setters must be well implemented"){
    /*Current HP setter*/
    character.CurrentHP = 7
    assertEquals(character.CurrentHP,7)
    /*Current stars setter*/
    character.CurrentStars = 20
    assertEquals(character.CurrentStars,20)
    /*Current victories setter*/
    character.Victories = 3
    assertEquals(character.Victories,3)
    /*Evade setter*/
    character.Evade = true
    assertEquals(character.Evade,true)
    /*Defend setter*/
    character.Defend = true
    assertEquals(character.Defend,true)
  }
  test("A character should be able to do an attack"){
    /*if the character is K.O, it shouldn't attack*/
    character.KO = true
    character.Attack(enemy)
    assertEquals(character.Attack_Quantity, 0)
    /*if it isn't K.O:*/
    /*The Attack Quantity is set as 0, so after invoking the method, it has to be different than zero and positive*/
    character.KO = false
    character.Attack(enemy)
    assert(character.Attack_Quantity > 0 && character.Attack_Quantity > character.ATK)
    /*if the enemy has HP 0, it shouldn't attack*/
    enemy.CurrentHP = 0
    character.Attack(enemy)
    assertEquals(character.Attack_Quantity,0)
    /*now we try with different types of enemies*/
    character.Attack_Quantity = 0
    character.Attack(enemy2)
    assert(character.Attack_Quantity > 0 && character.Attack_Quantity > character.ATK)
    character.Attack_Quantity = 0
    character.Attack(enemy3)
    assert(character.Attack_Quantity > 0 && character.Attack_Quantity > character.ATK)
    character.Attack_Quantity = 0
    enemy4.Evade = true
    character.Attack(enemy4)
    assert(character.Attack_Quantity > 0 && character.Attack_Quantity > character.ATK)
    /*If the enemy is a player and is KO, the it shouldn't attack*/
    enemy4.KO = true
    character.Attack(enemy4)
    assert(character.Attack_Quantity == 0)
  }
  test("A character receives stars when it defeats the enemy and the enemy loses them"){
    /*first we try with a wildUnit*/
    enemy.CurrentHP = 1
    enemy.CurrentStars = 10
    character.CurrentStars = 0
    character.Attack(enemy)
    assertEquals(character.CurrentStars, 13)
    assertEquals(enemy.CurrentStars,0)
    /*now, with other character*/
    enemy4.KO = false
    enemy4.CurrentHP = 1
    enemy4.CurrentStars = 10
    /*let's pretend that the enemy chose to defend itself after the attack, this then will be controlled with the input*/
    enemy4.Defend = true
    character.Attack(enemy4)
    assertEquals(character.CurrentStars, 18)
    assertEquals(enemy4.CurrentStars,5)
  }
  test("A character is in State KO after being defeated"){
    enemy4.Evade = true
    enemy4.CurrentHP=1
    character.Attack(enemy4)
    assert(enemy4.KO)
  }
  test("A character increases the amount of victories after defeating an enemy"){
    enemy.CurrentHP = 1
    character.Attack(enemy)
    assertEquals(character.Victories, 1)

    enemy4.Defend=true
    enemy4.CurrentHP=1
    character.Attack(enemy4)
    assertEquals(character.Victories,3)
  }
  test("A character should be able to defend itself") {
    val HP_before: Int = character.CurrentHP
    character.Defense(12)
    /*After invoking the method, the Current HP of the character has to be at least one unit less than the value before*/
    assert(character.CurrentHP<HP_before)
    /*if the HP was already equal to zero, it stays the same*/
    character.CurrentHP =0
    character.Defense(12)
    assert(character.CurrentHP == 0)
  }
  test("A character should be able to evade an attack") {
    val HP_before: Int = character.CurrentHP
    character.Evasion(12)
    /*After invoking the method, the character receives damage equal to 0 or the same quantity of the attack*/
    assert(character.CurrentHP == HP_before || character.CurrentHP < HP_before)
    /*If it's HP is already equal to zero, it stays the same*/
    character.CurrentHP = 0
    character.Evasion(12)
    assert(character.CurrentHP == 0)
  }
}
