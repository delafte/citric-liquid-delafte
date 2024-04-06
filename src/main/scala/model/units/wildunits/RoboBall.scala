package cl.uchile.dcc.citric
package model.units.wildunits

import cl.uchile.dcc.citric.model.panels.paneltypes.EncounterPanel
import cl.uchile.dcc.citric.model.units.players.PlayerCharacter
import cl.uchile.dcc.citric.model.units.traitunits.Unity

import scala.util.Random

/** The 'RoboBall' class represents one type of the wild units that appears aleatory on the board of the game
 * exclusively, on the Encounter Panels.
 * This wild unit extends from the trait Unity, so that we can define the specific values and variables.
 *
 * RoboBalls have determined values that differentiates them from the others Wild Units.
 * - ATK = -1
 * - DEF = 1
 * - EVA = -1
 * - maxHP = 3
 *
 * @constructor Creates a RoboBall wild unit with a specified encounter panel.
 *
 * @param _EncounterPanel An Encounter Panel in which the RoboBall is going to appear.
 * @param _randomNumberGenerator A utility to generate random numbers. Defaults to a new `Random` instance
 *
 * @example
 * {{{
 *   val robob: RoboBall = new RoboBall(EncounterPanel1, random)
 *   val robob_attack: Int = rob.ATK
 *   println(s"the attack of a RoboBall is $robob_attack")
 * }}}
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
class RoboBall(protected val _EncounterPanel: EncounterPanel, override protected val _randomNumberGenerator: Random = new Random) extends AbstractWildUnit(3,-1,1,-1,2){
  /**Makes appear a new RoboBall in the encounter panel, removing the current one*/
  def respawn(): Unit = {
    _EncounterPanel.remove(this)
    _EncounterPanel.wildUnit+=new RoboBall(_EncounterPanel)
  }
}
