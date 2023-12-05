package cl.uchile.dcc.citric
package model.controlador
import model.norm.Norm

trait Observer[T]{
  def update(observable: Subject, value: T): Unit

}
