package io.sengokudaikon.isn.chargen.domain.model

import io.sengokudaikon.isn.compendium.domain.system.ActionSystem
import io.sengokudaikon.isn.infrastructure.domain.FeatureModel
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.codecs.kotlinx.ObjectIdSerializer
import org.bson.types.ObjectId

@OptIn(ExperimentalSerializationApi::class)
@Serializable
class DedicationModel(
    @SerialName("_id")
    @Serializable(with = ObjectIdSerializer::class)
    override val id: ObjectId,
    override val img: String,
    override val name: String,
    override val type: String,
    override val system: ActionSystem
) : FeatureModel
