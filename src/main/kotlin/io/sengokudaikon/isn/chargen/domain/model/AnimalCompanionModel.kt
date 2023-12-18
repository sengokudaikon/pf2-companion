@file:OptIn(ExperimentalSerializationApi::class)

package io.sengokudaikon.isn.chargen.domain.model

import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.GenericRule
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.domain.system.SystemModel
import io.sengokudaikon.isn.compendium.domain.system.Traits
import io.sengokudaikon.isn.infrastructure.domain.Model
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.codecs.kotlinx.ObjectIdSerializer
import org.bson.types.ObjectId

@Serializable
class AnimalCompanionModel(
    @SerialName("_id")
    @Serializable(with = ObjectIdSerializer::class)
    override val id: ObjectId,
    override val img: String,
    override val name: String,
    override val type: String,
    override val system: SystemModel
): Model {
    @Serializable
    data class AnimalCompanionSystem(
        override val description: DescriptionType,
        override val publication: Publication,
        override val traits: Traits?,
        override val rules: List<GenericRule>,
        val equipment: List<String>?,
        val armor: String,
        val specializations: List<String>,
        val maturity: String,

    ): SystemModel
    override fun getSerializer(): KSerializer<*> = serializer()
}