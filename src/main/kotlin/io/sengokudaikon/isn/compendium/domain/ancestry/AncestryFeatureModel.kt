package io.sengokudaikon.isn.compendium.domain.ancestry

import io.sengokudaikon.isn.compendium.domain.Model
import io.sengokudaikon.isn.compendium.domain.system.ActionSystem
import io.sengokudaikon.isn.compendium.operations.character.ancestry.response.AncestryFeatureResponse
import kotlinx.serialization.Contextual
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Suppress("ConstructorParameterNaming")
@Serializable
data class AncestryFeatureModel(
    @SerialName("_id")
    @Contextual
    override val id: ObjectId,
    override val img: String,
    override val name: String,
    override val type: String,
    override val system: ActionSystem,
) : Model {
    fun toResponse(): AncestryFeatureResponse {
        return AncestryFeatureResponse(
            id = id.toHexString(),
            name = name,
            type = type,
            system = system.toResponse(),
        )
    }

    override fun getSerializer(): KSerializer<*> = serializer()
}
