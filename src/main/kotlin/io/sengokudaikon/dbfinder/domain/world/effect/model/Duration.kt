package io.sengokudaikon.dbfinder.domain.world.effect.model

import kotlinx.serialization.Serializable

@Serializable
data class Duration(
    val expiry: String,
    val sustained: Boolean,
    val unit: String,
    val value: Int,
)
