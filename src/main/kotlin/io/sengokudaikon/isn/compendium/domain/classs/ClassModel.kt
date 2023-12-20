package io.sengokudaikon.isn.compendium.domain.classs

import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.Item
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.domain.system.SystemModel
import io.sengokudaikon.isn.compendium.domain.system.Traits
import io.sengokudaikon.isn.infrastructure.domain.Model
import kotlinx.serialization.Contextual
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.BsonValue
import org.bson.codecs.kotlinx.BsonValueSerializer
import org.bson.types.ObjectId

@Suppress("ConstructorParameterNaming")
@Serializable
@OptIn(ExperimentalSerializationApi::class)
data class ClassModel(
    @SerialName("_id")
    @Contextual
    override val id: ObjectId,
    override val img: String,
    override val name: String,
    override val system: SystemProperty,
    override val type: String,
) : Model {
    var features: List<ClassFeatureModel> = emptyList()

    @Serializable
    data class SystemProperty(
        override val description: DescriptionType,
        override val publication: Publication,
        @OptIn(ExperimentalSerializationApi::class)
        @Serializable(with = BsonValueSerializer::class) override val rules: BsonValue? = null,
        override val traits: Traits,
        val attacks: Attacks,
        val defenses: Defenses,
        val hp: Int,
        val items: Map<String, Item>,
        val perception: Int,
        val savingThrows: SavingThrows,
        val trainedSkills: TrainedSkills,
        val spellcasting: Int,
        @Serializable(with = BsonValueSerializer::class) val ancestryFeatLevels: BsonValue,
        @Serializable(with = BsonValueSerializer::class) val classFeatLevels: BsonValue,
        @Serializable(with = BsonValueSerializer::class) val generalFeatLevels: BsonValue,
        @Serializable(with = BsonValueSerializer::class) val keyAbility: BsonValue,
        @Serializable(with = BsonValueSerializer::class) val skillFeatLevels: BsonValue,
        @Serializable(with = BsonValueSerializer::class) val skillIncreaseLevels: BsonValue,
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
}
