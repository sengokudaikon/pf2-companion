package io.sengokudaikon.isn.compendium.domain.condition

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
@Suppress("ConstructorParameterNaming")
data class ConditionModel(
    @SerialName("_id")
    @Contextual
    override val id: ObjectId,
    override val img: String,
    override val name: String,
    override val type: String,
    override val system: SystemProperty,
) : Model {
    @Serializable
    data class SystemProperty
    @OptIn(ExperimentalSerializationApi::class)
    constructor(
        override val description: DescriptionType,
        @Serializable(with = BsonValueSerializer::class) val duration: BsonValue,
        val group: String,
        val overrides: List<String>,
        override val publication: Publication,
        val references: References,
        @OptIn(ExperimentalSerializationApi::class)
        @Serializable(with = BsonValueSerializer::class) override val rules: BsonValue? = null,
        override val traits: Traits,
    ) : SystemModel {

        @Serializable
        data class References(
            val children: List<String>,
            val immunityFrom: List<String>,
            val overriddenBy: List<String>,
            val overrides: List<String>,
        )
    }
}
