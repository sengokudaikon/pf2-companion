package io.sengokudaikon.isn.compendium.domain.hazard

import io.sengokudaikon.isn.compendium.domain.Model
import io.sengokudaikon.isn.compendium.domain.system.SystemModel
import kotlinx.serialization.Contextual
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class HazardModel(
    @SerialName("_id")
    @Contextual
    override val id: ObjectId,
    override val img: String,
    override val name: String,
    override val type: String,
    override val system: SystemModel,
) : Model {
    override fun getSerializer(): KSerializer<*> = serializer()
}
