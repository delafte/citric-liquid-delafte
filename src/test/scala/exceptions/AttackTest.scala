package cl.uchile.dcc.citric
package exceptions
import model.units.wildunits.{Seagull,Chicken,RoboBall}
import model.panels.paneltypes.EncounterPanel
import scala.util.Random
class AttackTest extends munit.FunSuite {
  private val WU1: Seagull = new Seagull(new EncounterPanel, new Random(11))
  private val WU2: Chicken = new Chicken(new EncounterPanel, new Random(11))
  private val WU3: RoboBall = new RoboBall(new EncounterPanel, new Random(11))

  test("When we try to implement a fight between two wild units, an exception is thrown") {
    interceptMessage[InvalidAttackException]("An invalid Attack was found -- Two WildUnits can't fight between each other.") {
      WU1.Attack(WU2)
    }
    interceptMessage[InvalidAttackException]("An invalid Attack was found -- Two WildUnits can't fight between each other.") {
      WU2.Attack(WU3)
    }
    interceptMessage[InvalidAttackException]("An invalid Attack was found -- Two WildUnits can't fight between each other.") {
      WU1.Attack(WU3)
    }
    interceptMessage[InvalidAttackException]("An invalid Attack was found -- Two WildUnits can't fight between each other.") {
      WU1.Attack(WU1)
    }
  }
  test("with try and catch"){
    try{
      WU1.Attack(WU2)
    }catch{
      case e: InvalidAttackException => println("Two WildUnits can't fight between each other.")
    }
  }
}
