package cl.uchile.dcc.citric
package model.norm

import model.units.players.PlayerCharacter
/**This Trait is the base for all types of Norms that this game can have. Every Norm should
 * have the information of the objectives a character needs to be in that norm, the number
 * of the norm to use it in other methods and a method to upgrade the norm
 * @author [[https://github.com/delafte Delaney Tello E.]]*/
trait Norm {
  /** The amount of stars goals that the players have to reach for certain certain Norm. */
  protected val _Stars_obj: Int
  /** The amount of victories goals that the players have to reach for certain Norm. */
  protected val _Victories_obj: Int
  /**The number of the Norm*/
  protected val _NumberNorm: Int
  /**Returns the victories objective*/
  def Victories_obj: Int
  /**Returns the number of the norm*/
  def NumberNorm: Int
  /**Returns the stars objectives*/
  def Stars_obj: Int

  /**Upgrades the norm of a character
   * @param character the character with the norm that we want to upgrade*/
  def upgradeNorm(character: PlayerCharacter):Unit
}
