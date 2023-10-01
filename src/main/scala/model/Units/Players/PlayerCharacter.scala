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
  * @param _name The name of the player. This is an identifier and should be unique.
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
class PlayerCharacter(private val _name: String, maxHp: Int, attack: Int, defense: Int, evasion: Int, val randomNumberGenerator: Random = new Random()) extends Unity {
  protected val _maxHP: Int = maxHp
  protected val _ATK: Int = attack
  protected val _DEF: Int = defense
  protected val _EVA: Int = evasion
  /** This variable keeps track on the character's stars during the game */
  protected var _CurrentStars: Int = 0
  /** This variable keeps track on the amount of victories that the character has during the game */
  private var _Victories: Int = 0
  /** This variable keeps track on the Norm in which the character is in */
  private var _CurrentNorm: Int = 1
  /** This variable keeps track on the left HP of the character. It starts as maxHp */
  protected var _CurrentHP: Int = maxHp
  /** This variable indicates if the player completed the objectives for increasing the Norm
   * it is initialized to false */
  private var _NormCheck: Boolean = false

  /** This function gives the character 1 point of HP. It may be invoked when a character lands on a HomePanel */
  def heal(): Unit = {
    if (maxHp != _CurrentHP) {
      _CurrentHP += 1
    }
  }

  /** Rolls a dice and returns a value between 1 to 6. */
  def rollDice(): Int = {
    randomNumberGenerator.nextInt(6) + 1
  }
  /**Returns the name of the character*/
  def name: String = _name
  /**Returns the max HP of the character*/
  def MaxHP: Int = _maxHP
  /**Returns the attack of the character*/
  def ATK: Int = _ATK
  /**Returns the defense of the character*/
  def DEF: Int = _DEF
  /**Returns the evasion of the character*/
  def EVA: Int = _EVA
  /**Returns the current stars of the character*/
  def CurrentStars: Int = _CurrentStars
  /**Returns the current norm of the character*/
  def CurrentNorm: Int = _CurrentNorm
  /**Returns the Current HP of the Character*/
  def CurrentHP: Int = _CurrentHP
  /**Returns the NormCheck state of the character*/
  def NormCheck: Boolean = _NormCheck
  /**Returns the amount of victories of the character*/
  def Victories: Int = _Victories
  /**Updates the current amount of victories of the character
   * @param newVictories the new amount of victories of the character*/
  def Victories_=(newVictories:Int):Unit = {
    _Victories = newVictories
  }
  /**updates the current amount of stars of the character
   * @param Stars the new amount of stars*/
  def CurrentStars_=(Stars: Int): Unit = {
    _CurrentStars = Stars
  }
  /** Updates the current Norm of the player.
   *
   * @param newNorm The new Norm of the character.*/
  def CurrentNorm_=(newNorm: Int): Unit = {
    _CurrentNorm = newNorm
  }
  /** Updates the current HP of the player.
   *
   * @param newHP The new HP value.*/
  def CurrentHP_=(newHP: Int): Unit = {
    _CurrentHP = newHP
  }
  /** Updates the current NormCheck state of the player.
   *
   * @param newState The new state of their NormCheck.*/
  def NormCheck_=(newState: Boolean): Unit = {
    _NormCheck = newState
  }
}

