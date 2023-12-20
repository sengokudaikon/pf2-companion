package io.sengokudaikon.isn.infrastructure.operations.response

import io.sengokudaikon.isn.infrastructure.domain.Model
import kotlinx.serialization.Serializable

@Serializable
sealed class Response<T : Model>
