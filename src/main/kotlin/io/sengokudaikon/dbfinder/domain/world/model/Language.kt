package io.sengokudaikon.dbfinder.domain.world.model

import kotlinx.serialization.Serializable

@Serializable
data class Language(
    val name: String,
    val description: String? = null,
)
