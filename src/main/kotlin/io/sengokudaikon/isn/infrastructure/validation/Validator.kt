package io.sengokudaikon.isn.infrastructure.validation

interface Validator<T> {
    fun validate(value: T): Boolean
}
