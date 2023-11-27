package io.sengokudaikon.isn.compendium.domain.character.ancestry.model

import kotlinx.serialization.Serializable

@Serializable
data class AbilityFlaw(
    val attribute: String?,
    val value: Int,
)
