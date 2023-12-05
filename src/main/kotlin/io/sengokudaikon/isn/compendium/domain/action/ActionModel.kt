package io.sengokudaikon.isn.compendium.domain.action

import io.sengokudaikon.isn.compendium.domain.Model
import io.sengokudaikon.isn.compendium.domain.feat.FeatEffectModel
import io.sengokudaikon.isn.compendium.domain.system.ActionSystem
import kotlinx.serialization.Contextual
import kotlinx.serialization.KSerializer
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
) : Model {
    var effect: FeatEffectModel? = null
    override fun getSerializer(): KSerializer<ActionModel> = serializer()
}