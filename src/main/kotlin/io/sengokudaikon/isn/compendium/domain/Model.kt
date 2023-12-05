package io.sengokudaikon.isn.compendium.domain

import io.sengokudaikon.isn.compendium.domain.system.SystemModel
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.codecs.kotlinx.ObjectIdSerializer
import org.bson.types.ObjectId

@OptIn(ExperimentalSerializationApi::class)
@Suppress("ConstructorParameterNaming", "VariableNaming")
interface Model {
    @SerialName("_id")
    @Serializable(with = ObjectIdSerializer::class)
    val id: ObjectId
    val img: String
    val name: String
    val type: String
    val system: SystemModel

    fun getSerializer(): KSerializer<*>
}
