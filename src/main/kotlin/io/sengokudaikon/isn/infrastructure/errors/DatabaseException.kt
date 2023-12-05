package io.sengokudaikon.isn.infrastructure.errors

sealed class DatabaseException(
    message: String,
    val className: String?,
    previous: Throwable? = null
) : Throwable(message, cause = previous) {
    class NotFound(
        className: String?,
        message: String = "Entity $className not found",
        previous: Throwable? = null
    ) :
        DatabaseException(message, className, previous = previous)

    class ConnectionFailed(
        className: String?,
        message: String = "Connection to $className failed",
        previous: Throwable? = null
    ) :
        DatabaseException(message, className, previous)
}
