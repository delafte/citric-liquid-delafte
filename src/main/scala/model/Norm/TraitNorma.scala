package cl.uchile.dcc.citric
package model.Norm

trait TraitNorma {
  /** An array of two strings that indicate the objectives that the players can choose in between */
  protected val _objectives: List[String]
  /** An array with the amount of stars goals that the players have to reach in certain Norm. It increases from
   * norm 2 to 6 */
  protected val _Stars_obj: List[Int]
  /** An array with the amount of victories goals that the players have to reach in certain Norm. It increases
   * from norm 2 to 6 */
  protected val _Victories_obj: List[Int]
  /**Returns an immutable list of the victories objectives*/
  def Victories_obj: List[Int]
  /**Returns an immutable list of the stars objectives*/
  def Stars_obj: List[Int]
  /**Returns an immutable list of the objectives*/
  def objectives: List[String]
}
