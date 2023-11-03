package cl.uchile.dcc.citric
package model.units.traitunits
/**The trait 'Player' is the base for the creation of a PlayerCharacter, it includes fundamental things of this type
 * of unity that the others don't have.
 * @author [[https://github.com/delafte/ Delaney Tello E.]] */
trait Player extends Unity{
  /**Indicates if the character chose the stars objective to upgrade their norm*/
  protected var _Obj_stars:Boolean
  /**Indicates if the character chose the victories objective to upgrade their norm*/
  protected var _Obj_victories:Boolean
  /**The name of the PlayerCharacter*/
  protected val _name: String
  /**Indicates if the character is or not in a KO state*/
  protected var _KO: Boolean
  /**Indicates if the character chose to defend when they receive an attack*/
  protected var _Defend: Boolean
  /**Indicates if the character chose to defend when they receive an attack*/
  protected var _Evade: Boolean
  /** This function asks for the input of the user, so that the decision of defend or evade an attack
   * can be implemented. This function is called during the Attack process. */
  def DecideDefendOrEvade(): Unit
}
