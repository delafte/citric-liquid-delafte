package cl.uchile.dcc.citric
package model.normtest
import model.norm.{Norm1,Norm2,Norm3,Norm4,Norm5, Norm6}

import cl.uchile.dcc.citric.model.units.players.PlayerCharacter

class Norm6Test extends munit.FunSuite {
  /* The object under test*/
  private val norma: Norm6 = new Norm6()
  private val player: PlayerCharacter = new PlayerCharacter("Nini", 10, 5, 2, 1)
  test("A norm6 has an Int with the information of the amount required for being in that norm, for each stars and victories"){
    assertEquals(norma.Stars_obj,200)
    assertEquals(norma.Victories_obj,14)
  }
  test("If we upgrade a Norm6 it stays the same"){
    /*A character starts with Norm1*/
    player.CurrentNorm = new Norm6()
    assert(player.CurrentNorm.isInstanceOf[Norm6])
    player.Victories = 15
    player.CurrentNorm.upgradeNorm(player)
    assert(player.CurrentNorm.isInstanceOf[Norm6])
  }
}
