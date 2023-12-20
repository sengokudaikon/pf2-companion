package io.sengokudaikon.isn.infrastructure.operations.response

import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryFeatureModel
import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.Frequency
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.domain.system.SelfEffect
import io.sengokudaikon.isn.compendium.enums.ActionTypes
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AncestryFeatureResponse(
    val id: String,
    val name: String,
    @SerialName("type_")
    val type: String,
    val description: DescriptionType,
    val publication: Publication,
    val traits: TraitsResponse,
    val rules: String?,
    val frequency: Frequency?,
    val isDefault: String?,
    val actionType: ActionTypes,
    val actions: String?,
    val category: String,
    val level: Int?,
    val maxTakable: String?,
    val prerequisites: String?,
    val trigger: String?,
    val selfEffect: SelfEffect?
) : Response<AncestryFeatureModel>()
