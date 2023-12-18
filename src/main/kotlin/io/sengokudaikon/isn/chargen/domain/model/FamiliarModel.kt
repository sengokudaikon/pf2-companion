@file:OptIn(ExperimentalSerializationApi::class)

package io.sengokudaikon.isn.chargen.domain.model

import io.sengokudaikon.isn.compendium.domain.familiar.FamiliarAbilityModel
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
class FamiliarModel(
    @SerialName("_id")
    @Serializable(with = ObjectIdSerializer::class)
    override val id: ObjectId,
    override val img: String,
    override val name: String,
    override val type: String,
    override val system: SystemModel
): Model {
    @Serializable
    data class FamiliarSystem(
        override val description: DescriptionType,
        override val publication: Publication,
        override val traits: Traits?,
        override val rules: List<GenericRule>,
        val specific: String?,
        val equipment: List<String>?,
    ): SystemModel
    lateinit var abilities: List<FamiliarAbilityModel>
    override fun getSerializer(): KSerializer<*> = serializer()
}