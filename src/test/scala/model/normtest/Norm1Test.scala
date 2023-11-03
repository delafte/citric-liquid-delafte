package cl.uchile.dcc.citric
package model.normtest
import model.norm.{Norm1,Norm2,Norm5}

import cl.uchile.dcc.citric.model.units.players.PlayerCharacter

class Norm1Test extends munit.FunSuite {
  /* The object under test*/
  private val norma: Norm1 = new Norm1()
  private val player: PlayerCharacter = new PlayerCharacter("Nini", 10, 5, 2, 1)
  test("A norm1 has an Int with the information of the amount required for being in that norm, for each stars and victories"){
    assertEquals(norma.Stars_obj,0)
    assertEquals(norma.Victories_obj,0)
  }
  test("We can upgrade a Norm1 to a Norm2 of a character"){
    /*A character starts with Norm1*/
    assert(player.CurrentNorm.isInstanceOf[Norm1])
    player.Obj_victories = true /*the player chooses the victories objective*/
    player.Victories = 1 /*player has the requirement ok*/
    player.CurrentNorm.upgradeNorm(player)
    assert(player.CurrentNorm.isInstanceOf[Norm2])
    /*if it isn't ok, then it doesn't levels up*/
    player.CurrentNorm = new Norm1()
    player.Victories = 0
    player.CurrentNorm.upgradeNorm(player)
    assert(player.CurrentNorm.isInstanceOf[Norm1])
    /*it also works with the other objective*/
    player.Obj_victories = false
    player.Obj_stars = true /*the player chooses the stars objective*/
    player.CurrentStars = 10 /*player has the requirement ok*/
    player.CurrentNorm.upgradeNorm(player)
    assert(player.CurrentNorm.isInstanceOf[Norm2])
  }
}
