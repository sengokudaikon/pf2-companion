@file:OptIn(ExperimentalSerializationApi::class)

package io.sengokudaikon.isn.compendium.domain.heritage

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

@Serializable
data class HeritageModel(
    @SerialName("_id")
    @Contextual
    override val id: ObjectId,
    override val img: String,
    override val name: String,
    override val type: String,
    override val system: HeritageSystem,
) : Model {
    @Serializable
    data class HeritageSystem(
        override val description: DescriptionType,
        override val publication: Publication,
        override val traits: Traits?,
        @Serializable(with = BsonValueSerializer::class) override val rules: BsonValue?,
        val ancestry: Ancestry,
    ) : SystemModel

    @Serializable
    data class Ancestry(
        val name: String,
        val slug: String,
        val uuid: String,
    )
}
