package cl.uchile.dcc.citric
package model.normtest
import model.norm.{Norm1,Norm2,Norm3,Norm4}

import cl.uchile.dcc.citric.model.units.players.PlayerCharacter

class Norm3Test extends munit.FunSuite {
  /* The object under test*/
  private val norma: Norm3 = new Norm3()
  private val player: PlayerCharacter = new PlayerCharacter("Nini", 10, 5, 2, 1)
  test("A norm3 has an Int with the information of the amount required for being in that norm, for each stars and victories"){
    assertEquals(norma.Stars_obj,30)
    assertEquals(norma.Victories_obj,3)
  }
  test("We can upgrade a Norm2 to a Norm3 of a character"){
    /*A character starts with Norm1*/
    assert(player.CurrentNorm.isInstanceOf[Norm1])
    player.CurrentNorm = new Norm3() /*we set it with Norm3 to test the level up*/
    player.Obj_victories = true /*the player chooses the victories objective*/
    player.Victories = 7 /*player has the requirement ok*/
    player.CurrentNorm.upgradeNorm(player)
    assert(player.CurrentNorm.isInstanceOf[Norm4])
    /*if it isn't ok, then it doesn't levels up*/
    player.CurrentNorm = new Norm3()
    player.Victories = 5
    player.CurrentNorm.upgradeNorm(player)
    assert(player.CurrentNorm.isInstanceOf[Norm3])
    /*it also works with the other objective*/
    player.Obj_victories = false
    player.Obj_stars = true /*the player chooses the stars objective*/
    player.CurrentStars = 75 /*player has the requirement ok*/
    player.CurrentNorm.upgradeNorm(player)
    assert(player.CurrentNorm.isInstanceOf[Norm4])
  }
}
