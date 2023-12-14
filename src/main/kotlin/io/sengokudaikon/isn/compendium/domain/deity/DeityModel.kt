package io.sengokudaikon.isn.compendium.domain.deity

import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.GenericRule
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.domain.system.SystemModel
import io.sengokudaikon.isn.compendium.domain.system.Traits
import io.sengokudaikon.isn.infrastructure.domain.Model
import kotlinx.serialization.Contextual
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class DeityModel(
    @SerialName("_id")
    @Contextual
    override val id: ObjectId,
    override val img: String,
    override val name: String,
    override val type: String,
    override val system: SystemModel,
) : Model {
    @Serializable
    data class DeitySystemProperty(
        override val description: DescriptionType,
        override val publication: Publication,
        override val rules: List<GenericRule>,
        override val traits: Traits? = null,
        val ability: List<String>,
        val alignment: Alignment,
        val category: String,
        val domains: Domains,
        val font: List<String>,
        val skill: String,
        val spells: Spells,
        val weapons: List<String>,
    ) : SystemModel {
        @Serializable
        data class Alignment(
            val follower: List<String>,
            val own: String,
        )

        @Serializable
        data class Domains(
            val alternate: List<String>,
            val primary: List<String>,
        )

        @Serializable
        data class Spells(
            val level1: String,
            val level3: String,
            val level9: String,
        )
    }

    override fun getSerializer(): KSerializer<*> = serializer()
}
