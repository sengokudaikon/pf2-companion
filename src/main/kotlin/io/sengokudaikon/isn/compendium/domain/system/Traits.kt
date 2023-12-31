package io.sengokudaikon.isn.compendium.domain.system

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import org.bson.BsonValue
import org.bson.codecs.kotlinx.BsonValueSerializer

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class Traits(
    val otherTags: List<String> = listOf(),
    val rarity: String? = null,
    val value: List<String> = listOf(),
    val traditions: List<String> = listOf(),
    @Serializable(with = BsonValueSerializer::class) val selected: BsonValue? = null,
    @Serializable(with = BsonValueSerializer::class) val integrated: BsonValue? = null,
)
