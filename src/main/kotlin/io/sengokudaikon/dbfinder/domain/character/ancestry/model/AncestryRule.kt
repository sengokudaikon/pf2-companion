package io.sengokudaikon.dbfinder.domain.character.ancestry.model

import kotlinx.serialization.Serializable

@Serializable
data class AncestryRule(
    val name: String,
    val description: String,
)
