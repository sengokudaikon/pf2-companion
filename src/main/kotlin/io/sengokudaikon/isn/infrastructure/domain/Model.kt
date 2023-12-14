package io.sengokudaikon.isn.infrastructure.domain

import io.sengokudaikon.isn.compendium.domain.system.SystemModel
import io.sengokudaikon.isn.compendium.infrastructure.mapper.Mapper
import io.sengokudaikon.isn.infrastructure.operations.Response
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.codecs.kotlinx.ObjectIdSerializer
import org.bson.types.ObjectId
import org.koin.mp.KoinPlatform.getKoin

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
    fun <T : Model> toResponse(): Response<T> {
        val mapper = getKoin().get<Mapper<T>>()
        return mapper.toResponse(this as T)
    }
}
