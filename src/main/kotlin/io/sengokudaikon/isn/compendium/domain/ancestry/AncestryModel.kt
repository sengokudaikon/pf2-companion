package io.sengokudaikon.isn.compendium.domain.ancestry

import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.GenericRule
import io.sengokudaikon.isn.compendium.domain.system.Item
import io.sengokudaikon.isn.compendium.domain.system.Languages
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.domain.system.SystemModel
import io.sengokudaikon.isn.compendium.domain.system.Traits
import io.sengokudaikon.isn.compendium.enums.Ability
import io.sengokudaikon.isn.compendium.enums.fromShortName
import io.sengokudaikon.isn.infrastructure.domain.Model
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
