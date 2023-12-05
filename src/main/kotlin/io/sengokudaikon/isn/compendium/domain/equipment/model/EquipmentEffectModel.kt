package io.sengokudaikon.isn.compendium.domain.equipment.model

import io.sengokudaikon.isn.compendium.domain.Model
import io.sengokudaikon.isn.compendium.domain.effects.model.EffectSystem
import kotlinx.serialization.Contextual
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
class EquipmentEffectModel(
    @SerialName("_id")
    @Contextual
    override val id: ObjectId,
    override val img: String,
    override val name: String,
    override val type: String,
    override val system: EffectSystem,
) : Model {
    override fun getSerializer(): KSerializer<*> = serializer()
}
