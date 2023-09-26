package cl.uchile.dcc.citric
package model.Units.traitUnits

/** The 'Unity' Trait represents a unit, which is an entity that will participate
 * in the game. It can be a Character or a Wild Unit.
 *
 * Each Unity has its own parameters depending on its type, which can be determined
 * or is part of the game design.
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
trait Unity {
  /**The Unity's capability to deal damage to opponents.*/
  val ATK: Int

  /**The Unity's capability to resist or mitigate damage from opponents.*/
  val DEF: Int

  /**The Unity's skill to completely avoid certain attacks.*/
  val EVA: Int

  /**The maximum health points a Unity can have. It represents the Unity's endurance.*/
  val maxHP: Int

  /**The Current Stars that the Unity has during the game, it varies during the development of it*/
  var CurrentStars: Int

  /**The HP left that the Unity currently has, it varies during the game*/
  var CurrentHP:Int
}
