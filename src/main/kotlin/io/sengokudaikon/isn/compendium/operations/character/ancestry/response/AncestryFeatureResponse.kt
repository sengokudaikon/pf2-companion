package io.sengokudaikon.isn.compendium.operations.character.ancestry.response

import io.sengokudaikon.isn.compendium.operations.global.response.ActionResponse
import kotlinx.serialization.Serializable

@Serializable
data class AncestryFeatureResponse(
    val id: String,
    val name: String,
    val type: String,
    val system: ActionResponse
)
