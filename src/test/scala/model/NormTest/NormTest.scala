package cl.uchile.dcc.citric
package model.NormTest
import  cl.uchile.dcc.citric.model.Norm.Norm
import scala.collection.mutable.{ArrayBuffer, ArrayStack}

class NormTest extends munit.FunSuite {
  /* The object under test*/
  private val norma: Norm = new Norm()
  test("A norm has an Array with the information of the quantity required for leveling up the norm, for each stars and victories"){
    /*First, we test with the stars objectives*/
    val starsForNorm2: Int = 10
    val starsForNorm3: Int = 30
    val starsForNorm4: Int = 70
    val starsForNorm5: Int = 120
    val starsForNorm6: Int = 200
    assertEquals(norma.Stars_obj.head, starsForNorm2)
    assertEquals(norma.Stars_obj(1), starsForNorm3)
    assertEquals(norma.Stars_obj(2), starsForNorm4)
    assertEquals(norma.Stars_obj(3), starsForNorm5)
    assertEquals(norma.Stars_obj(4), starsForNorm6)
    /*Now, the victories objectives*/
    val victoriesForNorm2: Int = 1
    val victoriesForNorm3: Int = 3
    val victoriesForNorm4: Int = 6
    val victoriesForNorm5: Int = 10
    val victoriesForNorm6: Int = 14
    assertEquals(norma.Victories_obj.head,victoriesForNorm2)
    assertEquals(norma.Victories_obj(1), victoriesForNorm3)
    assertEquals(norma.Victories_obj(2), victoriesForNorm4)
    assertEquals(norma.Victories_obj(3), victoriesForNorm5)
    assertEquals(norma.Victories_obj(4), victoriesForNorm6)
  }
  test("A norm has the information of which objectives the characters can choose"){
    /*In this game, they can choose either stars or victories*/
    val stars: String="stars"
    val victories: String = "victories"
    assertEquals(norma.objectives(0), stars)
    assertEquals(norma.objectives(1), victories)
  }
}
