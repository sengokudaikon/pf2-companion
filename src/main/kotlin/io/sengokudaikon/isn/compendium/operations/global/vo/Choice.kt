package io.sengokudaikon.isn.compendium.operations.global.vo

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import org.bson.BsonValue
import org.bson.codecs.kotlinx.BsonValueSerializer

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class Choice(
    val label: String? = null,
    @Serializable(with = BsonValueSerializer::class)
    val value: BsonValue? = null,
)
