package io.sengokudaikon.isn.compendium.domain.ancestry

import io.sengokudaikon.isn.compendium.domain.Model
import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.GenericRule
import io.sengokudaikon.isn.compendium.domain.system.Item
import io.sengokudaikon.isn.compendium.domain.system.Languages
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.domain.system.SystemModel
import io.sengokudaikon.isn.compendium.domain.system.Traits
import io.sengokudaikon.isn.compendium.enums.Ability
import io.sengokudaikon.isn.compendium.enums.fromShortName
import io.sengokudaikon.isn.compendium.operations.character.ancestry.response.AncestryResponse
import io.sengokudaikon.isn.compendium.operations.global.response.SystemResponse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.codecs.kotlinx.ObjectIdSerializer
import org.bson.types.ObjectId

@OptIn(ExperimentalSerializationApi::class)
@Suppress("ConstructorParameterNaming")
@Serializable
data class AncestryModel(
    @SerialName("_id")
    @Serializable(with = ObjectIdSerializer::class)
    override val id: ObjectId,
    override val img: String,
    override val name: String,
    override val type: String,
    override val system: SystemProperty,
) : Model {
    override fun getSerializer(): KSerializer<*> = serializer()
    fun toResponse(): AncestryResponse {
        return AncestryResponse(
            id = id.toHexString(),
            name = name,
            type = type,
            system = system.toResponse(),
            ancestryFeatures = ancestryFeatures.mapValues { it.value.toResponse() },
        )
    }
    var ancestryFeatures: Map<String, AncestryFeatureModel> = emptyMap()

    @Serializable
    data class SystemProperty(
        override val description: DescriptionType,
        override val rules: List<GenericRule>,
        override val traits: Traits,
        override val publication: Publication,
        val additionalLanguages: Languages,
        val boosts: Map<String, AbilityScore>,
        val flaws: Map<String, AbilityScore>,
        val hp: Int,
        val languages: Languages,
        val reach: Int,
        val size: String,
        val source: String? = null,
        val speed: Int,
        val items: Map<String, Item>,
        val vision: String,
        val additionalSense: String?,
    ) : SystemModel {
        fun toResponse(): SystemResponse {
            return SystemResponse(
                description = description.value,
                rules = rules.map { it.toResponse() },
                traits = traits.toResponse(),
                publication = publication,
                additionalLanguages = additionalLanguages,
                boosts = boosts.toAbilityList(),
                flaws = flaws.toAbilityList(),
                hp = hp,
                languages = languages,
                reach = reach,
                size = size,
                source = source,
                speed = speed,
                items = items.mapValues { it.value.toResponse() },
                vision = vision,
                additionalSense = additionalSense,
            )
        }

        @Serializable
        data class AbilityScore(
            val value: List<String>,
        ) {
            fun toAbility(): Ability {
                return if (value.size > 1) {
                    Ability.Anything
                } else if (value.isEmpty()) {
                    Ability.None
                } else {
                    fromShortName(value.first())
                }
            }
        }
    }
}

private fun Map<String, AncestryModel.SystemProperty.AbilityScore>.toAbilityList(): List<Ability> {
    return this.map {
        it.value.toAbility()
    }
}
