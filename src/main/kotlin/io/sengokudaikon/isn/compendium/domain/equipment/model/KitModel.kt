package io.sengokudaikon.isn.compendium.domain.equipment.model

import io.sengokudaikon.isn.compendium.domain.equipment.dto.Price
import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.domain.system.SystemModel
import io.sengokudaikon.isn.compendium.domain.system.Traits
import io.sengokudaikon.isn.infrastructure.domain.Model
import kotlinx.serialization.Contextual
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.BsonValue
import org.bson.codecs.kotlinx.BsonValueSerializer
import org.bson.types.ObjectId

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class KitModel(
    @Contextual
    @SerialName("_id")
    override val id: ObjectId,
    override val img: String,
    override val name: String,
    override val type: String,
    override val system: SystemProperty
) : Model {
    @Serializable
    data class SystemProperty(
        override val description: DescriptionType,
        override val publication: Publication,
        override val traits: Traits?,
        @Serializable(with = BsonValueSerializer::class)
        override val rules: BsonValue?,
        @Serializable(with = BsonValueSerializer::class)
        val items: BsonValue?,
        val price: Price,
    ) : SystemModel
}
