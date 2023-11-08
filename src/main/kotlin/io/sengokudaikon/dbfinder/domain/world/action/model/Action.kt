package io.sengokudaikon.dbfinder.domain.world.action.model

import io.sengokudaikon.dbfinder.domain.character.feat.model.Item
import io.sengokudaikon.dbfinder.infrastructure.enums.ActionCategories
import io.sengokudaikon.dbfinder.infrastructure.enums.ActionTypes
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
