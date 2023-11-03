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
  /**Method that checks if the character meets the objective of the next Norm
   * and restarts the the attributes that indicate the objective decision of the character(in case of norm ascension)
   * This function is used in the UpgradeNorm method
   * @param norm the next Norm of the current norm of the character
   * @param character the character with the Norm we want to upgrade*/
  protected def GeneralUpgrade(norm:Norm, character: PlayerCharacter):Unit= {
    if ((character.CurrentStars >= norm.Stars_obj && character.Obj_stars) || (character.Victories >= norm.Victories_obj && character.Obj_victories)) {
      character.CurrentNorm = norm
      /*we use the setter to update the norm of the character*/
      /*and we use the setters of the Objectives to restart them*/
      character.Obj_stars = false
      character.Obj_victories = false
    }
  }
  /** Upgrades the norm of a character if they fulfilled the requirements of the objective that they chose
   *
   * @param character the character with the norm we want to upgrade
   * */
  def upgradeNorm(character: PlayerCharacter): Unit = {}
}
