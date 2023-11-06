package cl.uchile.dcc.citric
package model.normtest
import model.norm.{Norm1,Norm2,Norm3,Norm4,Norm5, Norm6}

import cl.uchile.dcc.citric.model.units.players.PlayerCharacter

class Norm5Test extends munit.FunSuite {
  /* The object under test*/
  private val norma: Norm5 = new Norm5()
  private val player: PlayerCharacter = new PlayerCharacter("Nini", 10, 5, 2, 1)
  test("A norm5 has an Int with the information of the amount required for being in that norm, for each stars and victories. Also its number"){
    assertEquals(norma.Stars_obj,120)
    assertEquals(norma.Victories_obj,10)
    assertEquals(norma.NumberNorm,5)
  }
  test("We can upgrade a Norm5 to a Norm6 of a character"){
    /*A character starts with Norm1*/
    assert(player.CurrentNorm.isInstanceOf[Norm1])
    player.CurrentNorm = new Norm5() /*we set it with Norm5 to test the level up*/
    player.Obj_victories = true /*the player chooses the victories objective*/
    player.addVictories(14) /*player has the requirement ok*/
    player.CurrentNorm.upgradeNorm(player)
    assert(player.CurrentNorm.isInstanceOf[Norm6])
    /*if it isn't ok, then it doesn't levels up*/
    player.CurrentNorm = new Norm5()
    player.removeVictories(2)
    player.CurrentNorm.upgradeNorm(player)
    assert(player.CurrentNorm.isInstanceOf[Norm5])
    /*it also works with the other objective*/
    /*first, let's verify that the objectives restarted*/
    assertEquals(player.Obj_victories, false)
    assertEquals(player.Obj_stars, false)

    player.Obj_stars = true /*the player chooses the stars objective*/
    player.addStars(201) /*player has the requirement ok*/
    player.CurrentNorm.upgradeNorm(player)
    assert(player.CurrentNorm.isInstanceOf[Norm6])
  }
}
