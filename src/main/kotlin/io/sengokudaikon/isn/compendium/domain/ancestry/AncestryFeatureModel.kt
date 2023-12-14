package io.sengokudaikon.isn.compendium.domain.ancestry

import io.sengokudaikon.isn.compendium.domain.system.ActionSystem
import io.sengokudaikon.isn.infrastructure.domain.FeatureModel
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
) : FeatureModel {
    override fun getSerializer(): KSerializer<AncestryFeatureModel> = serializer()
}
