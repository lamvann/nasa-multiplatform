package util

sealed class Failure {
    object NetworkFailure : Failure()
    object UnknownFailure : Failure()
}