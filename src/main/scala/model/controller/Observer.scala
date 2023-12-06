package cl.uchile.dcc.citric
package model.controller
/**This trait Observer is the base for the Observer in the implementation of the Observer Pattern. It only declares the update method which
 * will ensure the reception of the necessary information.
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]*/
trait Observer[T]{
  /**this method updates the observer with the value of interest
   * @param observable the subject they are observing
   * @param value the value of interest*/
  def update(observable: Subject, value: T): Unit

}
