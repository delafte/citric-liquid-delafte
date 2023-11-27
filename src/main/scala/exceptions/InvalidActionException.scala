package cl.uchile.dcc.citric
package exceptions

class InvalidActionException(message:String) extends Exception(s"An invalid Action was found -- $message")
