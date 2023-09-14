package io.sengokudaikon.dbfinder.domain.world.model

import kotlinx.serialization.Serializable

@Serializable
data class Trait(
    val name: String,
    val description: String,
    val isImportant: Boolean,
    val contentSrc: String,
    val isHidden: Boolean = false,
    val isArchived: Boolean = false,
    val homebrewID: Int? = null,
)
