package io.sengokudaikon.kfinder.infrastructure.validation

interface Validator<T> {
    fun validate(value: T): Boolean
}
