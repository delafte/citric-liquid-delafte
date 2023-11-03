package cl.uchile.dcc.citric
package model.units.players

import model.units.traitunits.Unity

import scala.util.Random
import model.units.wildunits.{Seagull,Chicken,RoboBall}
import model.units.traitunits.WildUnit
import model.norm.{Norm, Norm1, Norm2, Norm3, Norm4, Norm5, Norm6}
import scala.math._

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
  * @example
 * {{{
 *   val emma: PlayerCharacter= new PlayerCharacter("emma", 10, 5, 2, 1)
 * }}}
  * @param _name The name of the player. This is an identifier and should be unique.
  * @param maxHp The maximum health points a player can have. It represents the player's endurance.
  * @param attack The player's capability to deal damage to opponents.
  * @param defense The player's capability to resist or mitigate damage from opponents.
  * @param evasion The player's skill to completely avoid certain attacks.
  * @param _randomNumberGenerator A utility to generate random numbers. Defaults to a new `Random`
  *                              instance.
  * @author [[https://github.com/danielRamirezL/ Daniel Ramírez L.]]
  * @author [[https://github.com/joelriquelme/ Joel Riquelme P.]]
  * @author [[https://github.com/r8vnhill/ Ignacio Slater M.]]
  * @author [[https://github.com/Seivier/ Vicente González B.]]
  * @author [[https://github.com/delafte/ Delaney Tello E.]]
  */
class PlayerCharacter(protected val _name: String, maxHp: Int, attack: Int, defense: Int, evasion: Int, val _randomNumberGenerator: Random = new Random()) extends AbstractPlayerCharacter {
  protected val _maxHP: Int = maxHp
  protected val _ATK: Int = attack
  protected val _DEF: Int = defense
  protected val _EVA: Int = evasion
  protected var _CurrentHP: Int = maxHp

  /** This function gives the character 1 point of HP. It may be invoked when a character lands on a HomePanel */
  def heal(): Unit = {
    if (maxHp != _CurrentHP) {
      _CurrentHP += 1
    }
  }

  /** Returns the name of the character */
  def name: String = _name

  def Attack(enemy: Unity): Unit = {
    enemy.AttackPlayer(this)
  }
  def AttackPlayer(enemy: PlayerCharacter): Unit = {
    if (!KO && !enemy.KO) {
      GeneralATK(enemy)
      DecideDefendOrEvade() /*we call for the input of the user*/
      if (Defend) Defense(enemy.Attack_Quantity)
      else Evasion(enemy.Attack_Quantity)
      if (CurrentHP == 0) {
        val res: Int = floor(CurrentStars / 2).toInt
        enemy.CurrentStars += res
        CurrentStars -= res
        enemy.Victories += 2
        KO = true
      }
    }
    else enemy.Attack_Quantity = 0
  }

  def AttackWildUnit(enemy: WildUnit): Unit = {
    if (!KO && enemy.CurrentHP != 0) {
      GeneralATK(enemy)
      DecideDefendOrEvade() /*we call for the input of the user*/
      if (Defend) Defense(enemy.Attack_Quantity)
      else Evasion(enemy.Attack_Quantity)
      if (CurrentHP == 0) {
        val res: Int = floor(CurrentStars / 2).toInt
        enemy.CurrentStars += res
        CurrentStars -= res
        KO = true
      }
    }
    else enemy.Attack_Quantity = 0
  }
}
