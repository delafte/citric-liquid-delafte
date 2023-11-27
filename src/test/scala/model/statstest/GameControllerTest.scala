package cl.uchile.dcc.citric
package model.statstest
import munit.FunSuite
import model.controlador.GameController
class GameControllerTest extends FunSuite {
  private var game: GameController = null

  override def beforeEach(context: BeforeEach): Unit = {
    game = new GameController()
  }
  test("test PreGame"){
    assert(game.inPreGame())
  }
  test("test GameStarts"){
    game.GameStarts()
    assert(!game.inPreGame())
    assert(game.inChapter())
  }
  test("test new Chapter"){
    game.GameStarts()
    game.NewChapter()
    assert(!game.inPreGame())
    assert(!game.inEndOfGame())
    assert(!game.inPlayerTurn())
    assert(game.inChapter())
  }
  test("test start turn of player"){
    game.GameStarts()
    game.StartTurnPlayer()
    assert(!game.inPreGame())
    assert(!game.inChapter())
    assert(game.inPlayerTurn())
  }
  test("test PlayerKO"){
    game.GameStarts()
    game.StartTurnPlayer()
    game.PlayerKO()
    assert(!game.inPreGame())
    assert(!game.inChapter())
    assert(!game.inPlayerTurn())
    assert(game.inRecovery())
  }
  test("test insufficient roll"){
    game.GameStarts()
    game.StartTurnPlayer()
    game.PlayerKO()
    game.insufficientRoll()
    assert(!game.inPreGame())
    assert(!game.inPlayerTurn())
    assert(!game.inRecovery())
    assert(game.inChapter())
  }
  test("test sufficient roll"){
    game.GameStarts()
    game.StartTurnPlayer()
    game.PlayerKO()
    game.sufficientRoll()
    assert(!game.inPreGame())
    assert(!game.inPlayerTurn())
    assert(!game.inRecovery())
    assert(game.inChapter())
  }
  test("test rollDice_ChoosePath"){
    game.GameStarts()
    game.StartTurnPlayer()
    game.RollDice_ChoosePath()
    assert(!game.inPreGame())
    assert(!game.inPlayerTurn())
    assert(!game.inRecovery())
    assert(!game.inChapter())
    assert(game.inMoving())
  }
  test("test stop moving"){
    game.GameStarts()
    game.StartTurnPlayer()
    game.RollDice_ChoosePath()
    game.stopsMoving()
    assert(!game.inPreGame())
    assert(!game.inPlayerTurn())
    assert(!game.inRecovery())
    assert(!game.inChapter())
    assert(!game.inMoving())
    assert(game.inOnPanel())
  }
  test("test Out of moves") {
    game.GameStarts()
    game.StartTurnPlayer()
    game.RollDice_ChoosePath()
    game.OutOfMoves()
    assert(!game.inPreGame())
    assert(!game.inPlayerTurn())
    assert(!game.inRecovery())
    assert(!game.inChapter())
    assert(!game.inMoving())
    assert(game.inOnPanel())
  }
  test("test Decides Fight"){
    game.GameStarts()
    game.StartTurnPlayer()
    game.RollDice_ChoosePath()
    game.OutOfMoves()
    game.DecideFightCharacter()
    assert(!game.inPreGame())
    assert(!game.inPlayerTurn())
    assert(!game.inRecovery())
    assert(!game.inChapter())
    assert(!game.inMoving())
    assert(!game.inOnPanel())
    assert(game.inCombat())
  }
  test("test on encounter panel"){
    game.GameStarts()
    game.StartTurnPlayer()
    game.RollDice_ChoosePath()
    game.OutOfMoves()
    game.OnEncounterPanel()
    assert(!game.inPreGame())
    assert(!game.inPlayerTurn())
    assert(!game.inRecovery())
    assert(!game.inChapter())
    assert(!game.inMoving())
    assert(!game.inOnPanel())
    assert(game.inCombat())
  }
  test("test attacks"){
    game.GameStarts()
    game.StartTurnPlayer()
    game.RollDice_ChoosePath()
    game.OutOfMoves()
    game.OnEncounterPanel()
    game.Attacks()
    assert(!game.inPreGame())
    assert(!game.inPlayerTurn())
    assert(!game.inRecovery())
    assert(!game.inChapter())
    assert(!game.inMoving())
    assert(!game.inOnPanel())
    assert(!game.inCombat())
    assert(game.inWait())
  }
  test("test defends"){
    game.GameStarts()
    game.StartTurnPlayer()
    game.RollDice_ChoosePath()
    game.OutOfMoves()
    game.OnEncounterPanel()
    game.Attacks()
    game.defends()
    assert(!game.inPreGame())
    assert(!game.inPlayerTurn())
    assert(!game.inRecovery())
    assert(!game.inChapter())
    assert(!game.inMoving())
    assert(!game.inOnPanel())
    assert(!game.inWait())
    assert(game.inCombat())
  }
  test("test evades") {
    game.GameStarts()
    game.StartTurnPlayer()
    game.RollDice_ChoosePath()
    game.OutOfMoves()
    game.OnEncounterPanel()
    game.Attacks()
    game.Evades()
    assert(!game.inPreGame())
    assert(!game.inPlayerTurn())
    assert(!game.inRecovery())
    assert(!game.inChapter())
    assert(!game.inMoving())
    assert(!game.inOnPanel())
    assert(!game.inWait())
    assert(game.inCombat())
  }
  test("test End of combat"){
    game.GameStarts()
    game.StartTurnPlayer()
    game.RollDice_ChoosePath()
    game.OutOfMoves()
    game.DecideFightCharacter()
    game.Attacks()
    game.Evades()
    game.EndCombat()
    assert(!game.inPreGame())
    assert(!game.inPlayerTurn())
    assert(!game.inRecovery())
    assert(!game.inChapter())
    assert(!game.inMoving())
    assert(game.inOnPanel())
    assert(!game.inWait())
    assert(!game.inCombat())
  }
  test("test apply effect"){
    game.GameStarts()
    game.StartTurnPlayer()
    game.RollDice_ChoosePath()
    game.OutOfMoves()
    game.DecideFightCharacter()
    game.Attacks()
    game.Evades()
    game.EndCombat()
    game.applyEffect()
    assert(!game.inPreGame())
    assert(!game.inPlayerTurn())
    assert(!game.inRecovery())
    assert(game.inChapter())
    assert(!game.inMoving())
    assert(!game.inOnPanel())
    assert(!game.inWait())
    assert(!game.inCombat())
  }
  test("test Encounter finishes") {
    game.GameStarts()
    game.StartTurnPlayer()
    game.RollDice_ChoosePath()
    game.OutOfMoves()
    game.OnEncounterPanel()
    game.Attacks()
    game.Evades()
    game.EndCombat()
    game.FinishEncounterPanel()
    assert(!game.inPreGame())
    assert(!game.inPlayerTurn())
    assert(!game.inRecovery())
    assert(game.inChapter())
    assert(!game.inMoving())
    assert(!game.inOnPanel())
    assert(!game.inWait())
    assert(!game.inCombat())
  }

}
