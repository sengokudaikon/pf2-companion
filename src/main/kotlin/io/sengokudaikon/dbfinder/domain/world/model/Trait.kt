package io.sengokudaikon.dbfinder.domain.world.model

import kotlinx.serialization.Serializable

@Serializable
data class Trait(
    val name: String,
    val description: String,
    val contentSrc: String,
    val type: TraitType,
    val isHidden: Boolean = false,
    val isArchived: Boolean = false,
    val homebrewID: Int? = null,
)
