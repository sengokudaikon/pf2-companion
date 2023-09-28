package io.sengokudaikon.kfinder.infrastructure.errors

sealed class DatabaseException(message: String) : Throwable(message) {
    class NotFound(message: String = "No results found") : DatabaseException(message)
}
