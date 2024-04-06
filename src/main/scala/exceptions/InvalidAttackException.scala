package cl.uchile.dcc.citric
package exceptions
/**
 * Custom exception to signal an invalid intent of attack between WildUnits.
 *
 * This exception is designed to provide more specific feedback about this
 * invalid game function. For instance, if a combat between two WildUnits is tried to be implemented, this exception will be thrown with a detailed established
 * message indicating the nature of the problem.
 *
 * @example To throw the exception with the established details:
 * {{{
 * throw new InvalidAttackException()
 * // => InvalidAttackException: An invalid Attack was found -- Two WildUnits can't fight between each other.
 * }}}
 *
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
class InvalidAttackException extends Exception("An invalid Attack was found -- Two WildUnits can't fight between each other.")

