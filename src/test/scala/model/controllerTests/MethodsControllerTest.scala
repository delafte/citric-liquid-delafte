import cl.uchile.dcc.citric.model
import cl.uchile.dcc.citric.model.controlador.GameController
import munit.FunSuite
import cl.uchile.dcc.citric.model.panels.`trait`.Panel
import cl.uchile.dcc.citric.model.panels.paneltypes.{BonusPanel, DropPanel, EncounterPanel, HomePanel, NeutralPanel}
import cl.uchile.dcc.citric.model.units.players.PlayerCharacter

import scala.collection.mutable.ArrayBuffer
class MethodsControllerTest extends FunSuite {
  private var game: GameController = _
  private var playerCharacters: Seq[(String, Int, Int, Int, Int)] = Seq(("player1", 10, 5, 6, 3), ("player2", 10, 6, 7, 2))

  override def beforeEach(context: BeforeEach): Unit = {
    game = new GameController()
  }
  //test("star")
}
