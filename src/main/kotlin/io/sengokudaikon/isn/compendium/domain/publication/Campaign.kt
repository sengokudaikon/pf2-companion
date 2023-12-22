package io.sengokudaikon.isn.compendium.domain.publication

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
@Suppress("unused", "ConstructorParameterNaming")
data class Campaign(
    @SerialName("_id")
    @Contextual
    val id: ObjectId
)
