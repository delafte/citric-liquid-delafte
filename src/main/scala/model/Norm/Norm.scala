package cl.uchile.dcc.citric
package model.Norm
/**
 * The class 'Norm' represents the Norm of the game that the players have to rise up so that, the one first to reach
 * a Norm equal to 6, wins the match.
 * The players have to choose one of the two objectives for increasing their Norm level, it has to be chosen when the player levels up, and can't change it
 * until it's completed. Those objectives are reached with a certain quantity of stars or of victories.
 * After completing the requirements for leveling up, the player has to go to a HomePanel, so that they can activate the "Norm Check"
 * and increase their level.
 *
 * @constructor Creates a Norm.
 *
 * @example
 * {{{
 *   val norma: Norm = new Norm()
 *   val starsForSecondNorm: Int = norma.Stars_obj(1)
 *   println(s"the goal for reaching Norm 2 considering stars objective is $starsForSecondNorm")
 * }}}
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
/*the methods of this class will be add later*/
class Norm {
  /** An array of two strings that indicate the objectives that the players can choose in between */
  private val _objectives: List[String] = List("stars", "victories")
  /** An array with the amount of stars goals that the players have to reach in certain Norm. It increases from
   * norm 2 to 6*/
  private val _Stars_obj: List[Int] = List(10, 30, 70, 120, 200)
  /** An array with the amount of victories goals that the players have to reach in certain Norm. It increases
   * from norm 2 to 6*/
  private val _Victories_obj: List[Int] = List(1, 3, 6, 10, 14)
  /**Returns an immutable list of the victories objectives*/
  def Victories_obj: List[Int] = _Victories_obj
  /**Returns an immutable list of the stars objectives*/
  def Stars_obj: List[Int] = _Stars_obj
  /**Returns an immutable list of the objectives*/
  def objectives: List[String]= _objectives
  /*def NormCheck(character: PlayerCharacter): Unit = {}*/

  /*def NormClear(character: PlayerCharacter): Unit = {}*/
}
