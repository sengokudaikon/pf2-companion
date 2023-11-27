package io.sengokudaikon.isn.compendium.domain.world.action.model

import io.sengokudaikon.isn.compendium.domain.character.feat.model.Item
import io.sengokudaikon.isn.compendium.enums.ActionCategories
import io.sengokudaikon.isn.compendium.enums.ActionTypes
import kotlinx.serialization.Serializable

@Serializable
data class Action(
    val name: String,
    val image: String,
    val description: String,
    val actionType: ActionTypes,
    val actionNumber: Int?,
    val category: ActionCategories,
    val frequency: String,
    val requirements: String,
    val contentSrc: String,
    val trigger: String,
) : Item
