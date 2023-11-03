package cl.uchile.dcc.citric
package model.norm

import model.units.players.PlayerCharacter
/**The 'AbstractNorm' is an abstract class that contains all the methods that the different types
 * of norms have in common. With this implementation we avoid the repetition of code.
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]*/
abstract class AbstractNorm extends Norm {
  /** Returns the victories objective */
  def Victories_obj: Int = _Victories_obj

  /** Returns the stars objectives */
  def Stars_obj: Int = _Stars_obj
  /** Returns the number of the norm */
  def NumberNorm: Int = _NumberNorm

  /** Upgrades the norm of a character if they fulfilled the requirements of the objective that they chose
   *
   * @param character the character with the norm we want to upgrade
   * */
  def upgradeNorm(character: PlayerCharacter): Unit = {}
}
