package io.sengokudaikon.isn.compendium.domain.system

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import org.bson.BsonValue
import org.bson.codecs.kotlinx.BsonValueSerializer

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class ActionSystem(
    override val description: DescriptionType,
    override val publication: Publication,
    override val traits: Traits,
    @OptIn(ExperimentalSerializationApi::class)
    @Serializable(with = BsonValueSerializer::class) override val rules: BsonValue? = null,
    val frequency: Frequency?,
    val category: String? = null,
    val selfEffect: SelfEffect?,
    @Serializable(with = BsonValueSerializer::class) val prerequisites: BsonValue? = null,
    @Serializable(with = BsonValueSerializer::class) val isDefault: BsonValue? = null,
    @Serializable(with = BsonValueSerializer::class) val actionType: BsonValue? = null,
    @Serializable(with = BsonValueSerializer::class) val actions: BsonValue? = null,
    @Serializable(with = BsonValueSerializer::class) val level: BsonValue? = null,
    var takenAtLevel: Int? = null,
    var selectedChoice: String? = null,
    @Serializable(with = BsonValueSerializer::class) val maxTakable: BsonValue? = null,
    @Serializable(with = BsonValueSerializer::class) val trigger: BsonValue? = null,
    @Serializable(with = BsonValueSerializer::class) val weapon: BsonValue? = null,
) : SystemModel
