package cl.uchile.dcc.citric
package model.panels.paneltypes

import model.panels.abstractpanel.AbstractPanel
import model.panels.`trait`.Panel
import model.units.players.PlayerCharacter

import cl.uchile.dcc.citric.model.controlador.GameController
import cl.uchile.dcc.citric.model.units.traitunits.WildUnit
import cl.uchile.dcc.citric.model.units.wildunits.{Chicken, RoboBall, Seagull}

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/**
 * The 'EncounterPanel' class represents one type of the Panels that are on the board of the game.
 * This Panel extends from the abstract class AbstractPanel.
 * When a player lands on it, a combat with an aleatory WildUnit starts.
 *
 * @constructor Creates an EncounterPanel
 *
 * @example
 * {{{
 *   var panel1: EncounterPanel=new EncounterPanel()
 * }}}
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 *
 * */
/*this class will have a method in the future*/
class EncounterPanel() extends AbstractPanel {
  val name = "Encounter Panel"
  /**The wild units of the panel*/
  var wildUnit: ArrayBuffer[WildUnit] = ArrayBuffer(new Chicken(this), new Seagull(this), new RoboBall(this))
  /**This method selects a random wild unit to the encounter panel and returns it.*/
  def randomWildUnit(): WildUnit = {
    val ran = new Random()
    val result: Int =  ran.nextInt(3) + 1
    if(result == 1) wildUnit(0)
    else if(result==2) wildUnit(1)
    else wildUnit(2)
  }
  override def apply(player: PlayerCharacter, context: GameController): Unit = {
    if(characters.contains(player)){
      val enemy = randomWildUnit()
      context.currentEnemy = enemy
      context.encounterPanel = true
      context.fightWildUnit()
    }
  }
}
