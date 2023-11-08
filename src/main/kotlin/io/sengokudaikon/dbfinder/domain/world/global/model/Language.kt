package io.sengokudaikon.dbfinder.domain.world.global.model

import kotlinx.serialization.Serializable

@Serializable
data class Language(
    val name: String,
    val description: String? = null,
    val source: String? = null,
)
