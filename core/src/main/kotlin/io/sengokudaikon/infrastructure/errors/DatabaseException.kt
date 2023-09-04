package io.sengokudaikon.infrastructure.errors

sealed class DatabaseException(message: String): Exception(message) {
    class NotFound(message: String = "No results found") : DatabaseException(message)
}
