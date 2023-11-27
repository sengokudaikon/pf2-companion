package io.sengokudaikon.isn.infrastructure.errors

sealed class DatabaseException(message: String) : Throwable(message) {
    class NotFound(message: String = "No results found") : DatabaseException(message)
}
