package io.sengokudaikon.isn.compendium.domain.background

import io.sengokudaikon.isn.compendium.domain.action.ActionModel
import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryModel
import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.GenericRule
import io.sengokudaikon.isn.compendium.domain.system.Item
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.domain.system.SystemModel
import io.sengokudaikon.isn.compendium.domain.system.Traits
import io.sengokudaikon.isn.compendium.enums.Skills
import io.sengokudaikon.isn.infrastructure.domain.Model
import kotlinx.serialization.Contextual
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Suppress("ConstructorParameterNaming")
@Serializable
data class BackgroundModel(
    @SerialName("_id")
    @Contextual
    override val id: ObjectId,
    override val img: String,
    override val name: String,
    override val type: String,
    override val system: SystemProperty,
) : Model {
    lateinit var feats: Map<String, FeatModel>
    lateinit var actions: Map<String, ActionModel>
    override fun getSerializer(): KSerializer<*> = serializer()

    @Serializable
    data class SystemProperty(
        override val description: DescriptionType,
        override val rules: List<GenericRule>,
        override val publication: Publication,
        override val traits: Traits,
        val boosts: Map<String, AncestryModel.SystemProperty.AbilityScore>,
        val items: Map<String, Item>,
        val trainedLore: String,
        val trainedSkills: SkillField,
    ) : SystemModel {
        @Serializable
        data class SkillField(
            val value: List<String>
        ) {
            fun toSkillList(): List<Skills> {
                return value.map { Skills.from(it) }
            }
        }
    }
}
