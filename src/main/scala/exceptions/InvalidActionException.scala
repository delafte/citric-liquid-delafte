package cl.uchile.dcc.citric
package exceptions
/**
 * Custom exception to signal an invalid Action for certain states.
 *
 * This exception is designed to provide more specific feedback about this
 * invalid game function. For instance, if a not determined transition of states is implemented, this exception will be thrown with a detailed established
 * message indicating the nature of the problem.
 *
 * @example To throw the exception with the established details:
 * {{{
 * throw new InvalidActionException()
 * // => InvalidActionException: An invalid Action was found -- Cannot transition from Chapter to Combat.
 * }}}
 *
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
class InvalidActionException(message:String) extends Exception(s"An invalid Action was found -- $message")
