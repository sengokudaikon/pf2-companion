package io.sengokudaikon.isn.infrastructure.domain

import io.sengokudaikon.isn.compendium.domain.system.SystemModel
import io.sengokudaikon.isn.compendium.infrastructure.mapper.Mapper
import io.sengokudaikon.isn.compendium.infrastructure.mapper.ModelMapper
import io.sengokudaikon.isn.infrastructure.operations.response.Response
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.codecs.kotlinx.ObjectIdSerializer
import org.bson.types.ObjectId
import org.koin.java.KoinJavaComponent.getKoin

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

    fun getMapper(): Mapper<out Model> {
        return getKoin().get<Mapper<out Model>>()
    }

    fun <T : Model> toResponse(): Response<out Model> {
        return ModelMapper().toResponse(this)
    }
}
