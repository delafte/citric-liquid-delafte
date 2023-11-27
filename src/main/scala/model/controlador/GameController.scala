package cl.uchile.dcc.citric
package model.controlador
import model.controlador.Estados.PreGame
import model.controlador.State

import cl.uchile.dcc.citric.model.units.players.PlayerCharacter
import cl.uchile.dcc.citric.model.units.traitunits.WildUnit
import model.panels.`trait`.Panel

import scala.collection.mutable
import scala.util.Random
class GameController {
  private var state: State = new PreGame(this)
  private var playerCharacters = List.empty[PlayerCharacter]
  private var enemies = List.empty[WildUnit]
  private var panels = List.empty[Panel]
  private val turnsQueue = mutable.Queue.empty[Unit]
  def addPlayerCharacter(name:String, maxHP: Int, attack:Int, defense:Int, evasion:Int, randomNumberGenerator: Random = new Random()): Unit ={
    playerCharacters = new PlayerCharacter(name,maxHP, attack, defense, evasion, randomNumberGenerator) :: playerCharacters
  }

  def inPreGame(): Boolean = state.inPreGame()
  def inChapter(): Boolean = state.inChapter()
  def inCombat(): Boolean = state.inCombat()
  def inEndOfGame(): Boolean = state.inEndOfGame()
  def inMoving(): Boolean = state.inMoving()
  def inOnPanel(): Boolean = state.inOnPanel()
  def inPlayerTurn(): Boolean = state.inPlayerTurn()
  def inRecovery(): Boolean = state.inRecovery()
  def inWait(): Boolean = state.inWait()
  def setState(s:State): Unit = {
    state=s
    s.setGameController(this)
  }

  /** This method is for the transition from pre-game state to Chapter state */
  def GameStarts(): Unit = state.GameStarts()

  /** This method is for staying in Chapter State */
  def NewChapter(): Unit = state.NewChapter()

  /** This method is for the transition from the chapter state to the End of Game state */
  def ReachNorm6(): Unit = state.ReachNorm6()

  /** This method is for the transition from the Chapter state to de Player Turn state */
  def StartTurnPlayer(): Unit = state.StartTurnPlayer()

  /** this method is for the transition from the PlayerTurn State to the Moving state */
  def RollDice_ChoosePath(): Unit = state.RollDice_ChoosePath()

  /** this method is for the transition from the PlayerTurn state to the RecoveryState */
  def PlayerKO(): Unit = state.PlayerKO()

  /** This method is for the transition form the Recovery state to the Chapter State */
  def insufficientRoll(): Unit = state.insufficientRoll()

  /** This method is for the transition from the Recovery state to the PlayerTurn state */
  def sufficientRoll(): Unit = state.sufficientRoll()

  /** this method is for the transition from the Moving state to the OnPanel state */
  def stopsMoving(): Unit = state.stopsMoving()

  /** this method is for the transition from the Moving state to the OnPanel state */
  def OutOfMoves(): Unit = state.OutOfMoves()

  /** this method is for the transition from the OnPanel state to the Combat state */
  def DecideFightCharacter(): Unit = state.DecideFightCharacter()

  /** this method is for the transition from thr OnPanel state to the Combat state */
  def OnEncounterPanel(): Unit = state.OnEncounterPanel()

  /** this method is for the transition from the Combat state to the OnPanel state */
  def EndCombat(): Unit = state.EndCombat()

  /** this method is for the transition from the Combat state to the wait state */
  def Attacks(): Unit = state.Attacks()

  /** this method is for the transition from the Wait state to the Combat state */
  def Evades(): Unit = state.Evades()

  /** this method is for the transition from the Wait state to the Combat state */
  def defends(): Unit = state.defends()

  /** this method is for the transition from the OnPanel state to the Chapter state */
  def applyEffect(): Unit = state.applyEffect()

  /** this method id for the transition from the OnPanel state to the Chapter state */
  def FinishEncounterPanel(): Unit = state.FinishEncounterPanel()

}
