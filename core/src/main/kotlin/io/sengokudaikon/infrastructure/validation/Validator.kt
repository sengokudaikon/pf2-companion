package io.sengokudaikon.infrastructure.validation

interface Validator<T> {
    fun validate(value: T): Boolean
}
