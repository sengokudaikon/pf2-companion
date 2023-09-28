package io.sengokudaikon.dbfinder.domain.world.model

import io.sengokudaikon.dbfinder.domain.character.feat.model.Item
import kotlinx.serialization.Serializable

@Serializable
data class Action(
    val name: String,
    val image: String,
    val description: String,
    val actionType: String,
    val actionNumber: Int?,
    val category: String,
    val frequency: String,
    val requirements: String,
    val contentSrc: String,
    val trigger: String,
) : Item
