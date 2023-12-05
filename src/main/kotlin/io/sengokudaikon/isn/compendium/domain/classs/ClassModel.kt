package io.sengokudaikon.isn.compendium.domain.classs

import io.sengokudaikon.isn.compendium.domain.Model
import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.GenericRule
import io.sengokudaikon.isn.compendium.domain.system.Item
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.domain.system.SystemModel
import io.sengokudaikon.isn.compendium.domain.system.Traits
import kotlinx.serialization.Contextual
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import org.bson.types.ObjectId

@Suppress("ConstructorParameterNaming")
@Serializable
data class ClassModel(
    @SerialName("_id")
    @Contextual
    override val id: ObjectId,
    override val img: String,
    override val name: String,
    override val system: SystemProperty,
    override val type: String,
) : Model {
    @Serializable
    data class SystemProperty(
        override val description: DescriptionType,
        override val publication: Publication,
        override val rules: List<GenericRule>,
        override val traits: Traits,
        val ancestryFeatLevels: JsonObject,
        val attacks: Attacks,
        val classDC: Int,
        val classFeatLevels: JsonObject,
        val defenses: Defenses,
        val generalFeatLevels: JsonObject,
        val hp: Int,
        val items: Map<String, Item>,
        val keyAbility: JsonObject,
        val perception: Int,
        val savingThrows: SavingThrows,
        val skillFeatLevels: JsonObject,
        val skillIncreaseLevels: JsonObject,
        val trainedSkills: TrainedSkills,
    ) : SystemModel

    @Serializable
    data class Attacks(
        val advanced: Int,
        val martial: Int,
        val other: Other,
        val simple: Int,
        val unarmed: Int,
    )

    @Serializable
    data class Other(
        val name: String,
        val rank: Int,
    )

    @Serializable
    data class Defenses(
        val heavy: Int,
        val light: Int,
        val medium: Int,
        val unarmored: Int,
    )

    @Serializable
    data class SavingThrows(
        val fortitude: Int,
        val reflex: Int,
        val will: Int,
    )

    @Serializable
    data class TrainedSkills(
        val additional: Int,
        val value: List<String>,
    )

    override fun getSerializer(): KSerializer<*> = serializer()
}
