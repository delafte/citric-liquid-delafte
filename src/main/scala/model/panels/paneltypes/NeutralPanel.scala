package cl.uchile.dcc.citric
package model.panels.paneltypes

import cl.uchile.dcc.citric.model.panels.abstractpanel.AbstractPanel
import cl.uchile.dcc.citric.model.panels.`trait`.Panel
import cl.uchile.dcc.citric.model.units.players.PlayerCharacter

import scala.collection.mutable.ArrayBuffer
/**
 * The 'NeutralPanel' class represents one type of the Panels that are on the board of the game.
 * This Panel extends from the abstract class AbstractPanel, so that we inherit the methods required.
 *
 * This type of panel doesn't have any special effects on the players that land on it.
 * The utility of this class is just to identify/determine which are the panels that have no effects.
 *
 * @constructor Creates a NeutralPanel.
 *
 * @example
 * {{{
 *   var panel1: NeutralPanel= new NeutralPanel()
 * }}}
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 * */
class NeutralPanel() extends  AbstractPanel
