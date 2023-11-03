package cl.uchile.dcc.citric
package model.units.traitunits
import model.panels.paneltypes.EncounterPanel

trait WildUnit extends Unity{
  /**The EncounterPanel in which the WildUnit will appear*/
  protected val _EncounterPanel: EncounterPanel
  /**Bonus stars that the WildUnit gives when it loses*/
  protected val _BonusStars: Int
}
