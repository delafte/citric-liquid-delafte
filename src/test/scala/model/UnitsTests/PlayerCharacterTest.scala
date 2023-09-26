package cl.uchile.dcc.citric
package model.UnitsTests

import model.Units.Players.PlayerCharacter

import scala.util.Random

class PlayerCharacterTest extends munit.FunSuite {

  private val name = "testPlayer"
  private val maxHp = 10
  private val attack = 1
  private val defense = 1
  private val evasion = 1
  /*
  This is the object under test.
  We initialize it in the beforeEach method so we can reuse it in all the tests.
  This is a good practice because it will reset the object before each test, so you don't have
  to worry about the state of the object between tests.
  */
  private var character: PlayerCharacter = _  // <- x = _ is the same as x = null

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
  }

  test("A character should have correctly set their attributes and variables") {
    assertEquals(character.name, name)
    assertEquals(character.maxHp, maxHp)
    assertEquals(character.attack, attack)
    assertEquals(character.defense, defense)
    assertEquals(character.evasion, evasion)
    assertEquals(character.CurrentStars, 0)
    assertEquals(character.Victories, 0)
    assertEquals(character.CurrentNorm, 1)
    assertEquals(character.CurrentHP, maxHp)
    assertEquals(character.NormCheck, false)
    assertEquals(character.objective, "choose")
  }

  // Two ways to test randomness (you can use any of them):

  // 1. Test invariant properties, e.g. the result is always between 1 and 6.
  test("A character should be able to roll a dice") {
    for (_ <- 1 to 10) {
      assert(character.rollDice >= 1 && character.rollDice <= 6)
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
  test("A character can be healed"){
    /*First, we test the case in which the character is already full Hp*/
    character.heal()
    assertEquals(character.CurrentHP,character.maxHp)
    /*Now the case in which the character isn't full hp*/
    character.CurrentHP=5
    character.heal()
    assertEquals(character.CurrentHP,character.maxHp)
  }
}
