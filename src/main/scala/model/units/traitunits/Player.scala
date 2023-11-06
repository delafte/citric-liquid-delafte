package cl.uchile.dcc.citric
package model.units.traitunits
import model.norm.Norm
/**The trait 'Player' is the base for the creation of a PlayerCharacter, it includes fundamental things of this type
 * of unity that the others don't have.
 * @author [[https://github.com/delafte/ Delaney Tello E.]] */
trait Player extends Unity{
  /**Indicates if the player chose the stars objective to upgrade their norm*/
  protected var _Obj_stars:Boolean
  /**Indicates if the player chose the victories objective to upgrade their norm*/
  protected var _Obj_victories:Boolean
  /**The name of the Player*/
  protected val _name: String
  /**Indicates if the player is or not in a KO state*/
  protected var _KO: Boolean
  /**Indicates if the player chose to defend when they receive an attack*/
  protected var _Defend: Boolean
  /**Indicates if the player chose to evade when they receive an attack*/
  protected var _Evade: Boolean
  /**The norm in which the Player is*/
  protected var _CurrentNorm: Norm
  /**The number of Victories that the player has*/
  protected var _Victories: Int
  /** This function asks for the input of the user, so that the decision of defend or evade an attack
   * can be implemented. This function is called during the Attack process. */
  protected def DecideDefendOrEvade(): Unit
}
