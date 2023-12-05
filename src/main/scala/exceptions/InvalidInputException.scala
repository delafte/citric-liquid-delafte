package cl.uchile.dcc.citric
package exceptions
/**
 * Custom exception to signal an invalid Input for certain inputs.
 *
 * This exception is designed to provide more specific feedback about this
 * invalid game function. For instance, if a not determined an input is used, this exception will be thrown with a detailed established
 * message indicating the nature of the problem.
 *
 * @example To throw the exception with the established details:
 * {{{
 * throw new InvalidInputException()
 * // => InvalidInputException: An invalid Input was found -- '1' id not a choice.
 * }}}
 *
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
class InvalidInputException(message:String) extends Exception(s"An invalid Input was found -- $message")