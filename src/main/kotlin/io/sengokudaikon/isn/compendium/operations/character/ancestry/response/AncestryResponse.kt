package io.sengokudaikon.isn.compendium.operations.character.ancestry.response

import io.sengokudaikon.isn.compendium.operations.global.response.SystemResponse
import kotlinx.serialization.Serializable

@Serializable
data class AncestryResponse(
    val id: String,
    val name: String,
    val type: String,
    val system: SystemResponse,
    val ancestryFeatures: Map<String, AncestryFeatureResponse>,
)
