package io.sengokudaikon.kfinder.infrastructure.validation

import io.sengokudaikon.infrastructure.validation.EmailValidator
import io.sengokudaikon.infrastructure.validation.PasswordValidator
import io.sengokudaikon.infrastructure.validation.PlayerNameValidator
import io.sengokudaikon.infrastructure.validation.UUIDValidator
import io.sengokudaikon.infrastructure.validation.UsernameValidator
import io.sengokudaikon.infrastructure.validation.constraints.ValidEmail
import io.sengokudaikon.infrastructure.validation.constraints.ValidPassword
import io.sengokudaikon.infrastructure.validation.constraints.ValidPlayerName
import io.sengokudaikon.infrastructure.validation.constraints.ValidUUID
import io.sengokudaikon.infrastructure.validation.constraints.ValidUsername
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties

@Suppress("UNCHECKED_CAST")
fun validate(obj: Any) {
    val clazz = obj::class
    for (property in clazz.declaredMemberProperties) {
        val value = (property as KProperty1<Any, *>).get(obj)

        property.annotations.forEach { annotation ->
            when (annotation) {
                is ValidEmail -> {
                    val validator = EmailValidator()
                    requireValidation(value as String, validator::validate, "Invalid email format")
                }
                is ValidPassword -> {
                    val validator = PasswordValidator()
                    requireValidation(value as String, validator::validate, "Invalid password format")
                }
                is ValidPlayerName -> {
                    val validator = PlayerNameValidator()
                    requireValidation(value as String, validator::validate, "Invalid player name format")
                }
                is ValidUsername -> {
                    val validator = UsernameValidator()
                    requireValidation(value as String, validator::validate, "Invalid username format")
                }
                is ValidUUID -> {
                    val validator = UUIDValidator()
                    requireValidation(value as String, validator::validate, "Invalid UUID format")
                }
            }
        }
    }
}

inline fun <T> requireValidation(value: T, predicate: (T) -> Boolean, lazyMessage: String) {
    require(predicate(value)) { lazyMessage }
}
