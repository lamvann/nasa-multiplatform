package util

import util.Either.Left
import util.Either.Right

/**
 * Simplified implementation the Either monad.
 *
 * @param F Type of a Left
 * @param S Type of a Right
 */
sealed class Either<out F, out S> {
    data class Left<out F>(val left: F) : Either<F, Nothing>()
    data class Right<out S>(val right: S) : Either<Nothing, S>()

    val isLeft: Boolean get() = this is Left<F>
    val isRight: Boolean get() = this is Right<S>

    /**
     * Gets the right value or null if Left
     *
     * @return S returns Right value or null if Left
     */
    fun getOrNull(): S? =
        when (this) {
            is Left -> null
            is Right -> right
        }

    /**
     * Gets the right value or null if Left
     *
     * @return S returns Right value or null if Left
     */
    fun leftOrNull(): F? =
        when (this) {
            is Left -> left
            is Right -> null
        }

    /**
     * Reduces Left or Right Type into a single [value]
     *
     * @param onLeft Lambda that converts Left into a value
     * @param onRight Lambda that converts Right into a value
     * @return C returns new [value]
     */
    inline fun <C> fold(onLeft: (F) -> C, onRight: (S) -> C): C =
        when (this) {
            is Left -> onLeft(left)
            is Right -> onRight(right)
        }

    /**
     * Transforms a Right to another value and creates another Either.
     * For Left, returns the same Either.
     *
     * @param transformer Lambda that converts right to another [value]
     * @return Either<F, C> returns new Either with new [value]
     */
    inline fun <C> map(transformer: (S) -> C): Either<F, C> =
        when (this) {
            is Left -> this
            is Right -> Right(transformer(right))
        }

    /**
     * Transforms a Left to another value and creates another Either.
     * For Right, returns the same Either.
     *
     * @param transformer Lambda that converts right to another [value]
     * @return Either<F, C> returns new Either with new [value]
     */
    inline fun <C> mapLeft(transformer: (F) -> C): Either<C, S> =
        when (this) {
            is Left -> Left(transformer(left))
            is Right -> this
        }

    /**
     * Transforms a Right to another value and creates another Either.
     * For Left, returns the same Either.
     *
     * @param transformer Lambda that converts right to another [value]
     * @return Either<F, C> returns new Either with new [value]
     */
    inline fun <C> mapRight(transformer: (S) -> C): Either<F, C> =
        when (this) {
            is Left -> this
            is Right -> Right(transformer(right))
        }
}

/**
 * Returns the right value or a default value instead
 *
 * @param F Type of left
 * @param S Type of right
 * @param defaultValue Default right value
 * @return S returns the right value or a default value instead
 */
fun <F, S> Either<F, S>.getOrDefault(defaultValue: S): S =
    when (this) {
        is Left -> defaultValue
        is Right -> right
    }

/**
 * Returns an Either based on whether or not it passes the predicate
 *
 * @param F Type of Left
 * @param S Type of Right
 * @param defaultValue Default left value
 * @param predicate lambda that returns true/false based on a condition
 * @return Either<F, S>
 * Returns itself if the Right value passes the predicate
 * Returns Right([defaultValue]) if the Right value fails the predicate
 * Returns itself if it is a Left
 */
inline fun <F, S> Either<F, S>.validateAndGet(defaultValue: S, predicate: (S) -> Boolean): Either<F, S> =
    when (this) {
        is Left -> this
        is Right -> if (predicate(right)) {
            this
        } else {
            Right(defaultValue)
        }
    }


inline fun <F, S> Either<F, S>.onSuccess(block: (S) -> Unit): Either<F, S> = apply {
    if (this is Right) block(right)
}

inline fun <F, S> Either<F, S>.onFailure(block: (F) -> Unit): Either<F, S> = apply {
    if (this is Left) block(left)
}