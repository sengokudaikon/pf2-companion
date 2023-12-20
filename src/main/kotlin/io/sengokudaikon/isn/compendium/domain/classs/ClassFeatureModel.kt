package io.sengokudaikon.isn.compendium.domain.classs

import io.sengokudaikon.isn.compendium.domain.system.ActionSystem
import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.domain.system.SystemModel
import io.sengokudaikon.isn.compendium.domain.system.Traits
import io.sengokudaikon.isn.infrastructure.domain.FeatureModel
import kotlinx.serialization.Contextual
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.BsonValue
import org.bson.codecs.kotlinx.BsonValueSerializer
import org.bson.types.ObjectId

@Suppress("ConstructorParameterNaming")
@Serializable
data class ClassFeatureModel(
    @SerialName("_id")
    @Contextual
    override val id: ObjectId,
    override val img: String,
    override val name: String,
    override val type: String,
    override val system: ActionSystem,
) : FeatureModel {
    @OptIn(ExperimentalSerializationApi::class)
    @Serializable
    data class ClassFeatureSystem(
        override val description: DescriptionType,
        override val publication: Publication,
        override val traits: Traits,
        @OptIn(ExperimentalSerializationApi::class)
        @Serializable(with = BsonValueSerializer::class) override val rules: BsonValue? = null,
        @Serializable(with = BsonValueSerializer::class) val actionType: BsonValue? = null,
        @Serializable(with = BsonValueSerializer::class) val actions: BsonValue? = null,
        @Serializable(with = BsonValueSerializer::class) val prerequisites: BsonValue? = null,
        val category: String,
        @Serializable(with = BsonValueSerializer::class) val level: BsonValue? = null,
    ) : SystemModel {
        var takenAtLevel: Int? = null
        var selectedChoice: String? = null
    }
}
