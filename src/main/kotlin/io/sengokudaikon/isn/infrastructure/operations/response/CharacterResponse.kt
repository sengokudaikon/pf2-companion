package io.sengokudaikon.isn.infrastructure.operations.response

import io.sengokudaikon.isn.chargen.domain.model.CharacterModel
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val id: String,
    val name: String,
    val description: String,
): Response<CharacterModel>()
