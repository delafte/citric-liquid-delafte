package cl.uchile.dcc.citric
package model.norm

import model.units.players.PlayerCharacter
/**This Trait is the base for all types of Norms that this game can have. Every Norm should
 * have the information of the objectives that a character needs to fulfill to be in that norm, the number
 * of the norm to use it in other methods and a method to upgrade the norm
 *
 * @author [[https://github.com/delafte Delaney Tello E.]]*/
trait Norm {

 /** The amount of victories goal that the players have to reach for certain Norm. */
  def Victories_obj: Int
 /** The amount of stars goal that the players have to reach for certain certain Norm. */
  def NumberNorm: Int
  /**The number of the Norm*/
  def Stars_obj: Int

  /**Upgrades the norm of a character
   * @param character the character with the norm that we want to upgrade*/
  def upgradeNorm(character: PlayerCharacter):Unit
}
