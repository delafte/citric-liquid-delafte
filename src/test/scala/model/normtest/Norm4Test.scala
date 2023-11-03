package cl.uchile.dcc.citric
package model.normtest
import model.norm.{Norm1,Norm2,Norm3,Norm4,Norm5}

import cl.uchile.dcc.citric.model.units.players.PlayerCharacter

class Norm4Test extends munit.FunSuite {
  /* The object under test*/
  private val norma: Norm4 = new Norm4()
  private val player: PlayerCharacter = new PlayerCharacter("Nini", 10, 5, 2, 1)
  test("A norm4 has an Int with the information of the amount required for being in that norm, for each stars and victories"){
    assertEquals(norma.Stars_obj,70)
    assertEquals(norma.Victories_obj,6)
  }
  test("We can upgrade a Norm4 to a Norm5 of a character"){
    /*A character starts with Norm1*/
    assert(player.CurrentNorm.isInstanceOf[Norm1])
    player.CurrentNorm = new Norm4() /*we set it with Norm4 to test the level up*/
    player.Obj_victories = true /*the player chooses the victories objective*/
    player.Victories = 11 /*player has the requirement ok*/
    player.CurrentNorm.upgradeNorm(player)
    assert(player.CurrentNorm.isInstanceOf[Norm5])
    /*if it isn't ok, then it doesn't levels up*/
    player.CurrentNorm = new Norm4()
    player.Victories = 9
    player.CurrentNorm.upgradeNorm(player)
    assert(player.CurrentNorm.isInstanceOf[Norm4])
    /*it also works with the other objective*/
    /*first, let's verify that the objectives restarted*/
    assertEquals(player.Obj_victories, false)
    assertEquals(player.Obj_stars, false)

    player.Obj_stars = true /*the player chooses the stars objective*/
    player.CurrentStars = 121 /*player has the requirement ok*/
    player.CurrentNorm.upgradeNorm(player)
    assert(player.CurrentNorm.isInstanceOf[Norm5])
  }
}
