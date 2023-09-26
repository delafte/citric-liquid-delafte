package cl.uchile.dcc.citric
package model.Units.Players

import cl.uchile.dcc.citric.model.Units.traitUnits.Unity
import scala.util.Random

/** The `PlayerCharacter` class represents a character or avatar in the game, encapsulating
  * several attributes such as health points, attack strength, defense capability,
  * and evasion skills. Each player has a unique name, and throughout the game,
  * players can collect stars, roll dice, and progress in levels known as 'norma'.
  * This class not only maintains the state of a player but also provides methods
  * to modify and interact with these attributes.
  *
  * For instance, players can:
 *
  * - Increase or decrease their star count.
 *
  * - Roll a dice, a common action in many board games.
 *
  * - Advance their norma level.
  *
  * Furthermore, the `Player` class has a utility for generating random numbers,
  * which is primarily used for simulating dice rolls. By default, this utility is
  * an instance of the `Random` class but can be replaced if different random
  * generation behaviors are desired.
  *
  * @constructor Creates a PlayerCharacter with specified name, maxHp, attack, defense, evasion and a random number
  *
  * @example
 * {{{
 *   val emma: PlayerCharacter= new PlayerCharacter("emma", 10, 5, 2, 1)
 * }}}
  *
  * @param name The name of the player. This is an identifier and should be unique.
  * @param maxHp The maximum health points a player can have. It represents the player's endurance.
  * @param attack The player's capability to deal damage to opponents.
  * @param defense The player's capability to resist or mitigate damage from opponents.
  * @param evasion The player's skill to completely avoid certain attacks.
  * @param randomNumberGenerator A utility to generate random numbers. Defaults to a new `Random`
  *                              instance.
  *
  * @author [[https://github.com/danielRamirezL/ Daniel Ramírez L.]]
  * @author [[https://github.com/joelriquelme/ Joel Riquelme P.]]
  * @author [[https://github.com/r8vnhill/ Ignacio Slater M.]]
  * @author [[https://github.com/Seivier/ Vicente González B.]]
  * @author [[https://github.com/delafte/ Delaney Tello E.]]
  */
class PlayerCharacter(val name: String, val maxHp: Int, val attack: Int, val defense: Int, val evasion: Int, val randomNumberGenerator: Random = new Random()) extends Unity {
  val maxHP: Int = maxHp
  val ATK: Int = attack
  val DEF: Int = defense
  val EVA: Int = evasion
  /**This variable keeps track on the character's stars during the game*/
  var CurrentStars: Int = 0
  /**This variable keeps track on the amount of victories that the character has during the game*/
  var Victories: Int = 0
  /**This variable keeps track on the Norm in which the character is in*/
  var CurrentNorm: Int = 1
  /**This variable keeps track on the left HP of the character. It starts as maxHp*/
  var CurrentHP: Int = maxHp
  /**This variable indicates if the player completed the objectives for increasing the Norm
   * it is initialized to false */
  var NormCheck: Boolean = false
  /**This function gives the character 1 point of HP. It may be invoked when a character lands on a HomePanel*/
  def heal(): Unit = {
    if(maxHp != CurrentHP){
      CurrentHP += 1
    }
  }

  /** Rolls a dice and returns a value between 1 to 6. */
  def rollDice(): Int = {
    randomNumberGenerator.nextInt(6) + 1
  }
}
