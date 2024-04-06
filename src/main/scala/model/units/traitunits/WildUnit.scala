package cl.uchile.dcc.citric
package model.units.traitunits
import model.panels.paneltypes.EncounterPanel
/**The trait 'WildUnit' represent the base for the enemies of this game. It extends the Unity trait to sum the EncounterPanel in which
 * the enemy appears and the BonusStars that each enemy has and gives at the moment of being defeated.
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 * */
trait WildUnit extends Unity{
  /**The EncounterPanel in which the WildUnit will appear*/
  protected val _EncounterPanel: EncounterPanel
  /**Bonus stars that the WildUnit gives when it loses*/
  protected val _BonusStars: Int
  /**Makes appear a new wild Unit in the encounter panel*/
  def respawn():Unit
}
