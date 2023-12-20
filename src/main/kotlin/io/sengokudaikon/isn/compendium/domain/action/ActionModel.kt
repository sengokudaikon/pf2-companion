package io.sengokudaikon.isn.compendium.domain.action

import io.sengokudaikon.isn.compendium.domain.feat.FeatEffectModel
import io.sengokudaikon.isn.compendium.domain.system.ActionSystem
import io.sengokudaikon.isn.infrastructure.domain.FeatureModel
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Suppress("ConstructorParameterNaming")
@Serializable
data class ActionModel(
    @SerialName("_id")
    @Contextual
    override val id: ObjectId,
    override val img: String,
    override val name: String,
    override val type: String,
    override val system: ActionSystem,
) : FeatureModel {
    var effect: FeatEffectModel? = null
}
