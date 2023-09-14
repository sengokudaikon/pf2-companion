package io.sengokudaikon.dbfinder.domain.character.ancestry.model

import io.sengokudaikon.dbfinder.domain.world.model.Language
import kotlinx.serialization.Serializable

@Serializable
data class AdditionalLanguages(
    val values: List<Language>,
)
