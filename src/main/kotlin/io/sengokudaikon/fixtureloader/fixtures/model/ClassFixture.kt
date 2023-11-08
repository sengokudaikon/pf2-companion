package io.sengokudaikon.fixtureloader.fixtures.model

import io.sengokudaikon.fixtureloader.fixtures.model.system.Item
import io.sengokudaikon.fixtureloader.fixtures.model.system.Publication
import io.sengokudaikon.fixtureloader.fixtures.model.system.Traits
import io.sengokudaikon.fixtureloader.fixtures.model.system.ValueType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Suppress("ConstructorParameterNaming")
@Serializable
data class ClassFixture(
    val _id: String,
    val img: String,
    val name: String,
    val system: System,
    val type: String,
) {
    @Serializable
    data class System(
        val ancestryFeatLevels: ValueType.IntListValue,
        val attacks: Attacks,
        val classDC: Int,
        val classFeatLevels: ValueType.IntListValue,
        val defenses: Defenses,
        val description: ValueType.StringValue,
        val generalFeatLevels: ValueType.IntListValue,
        val hp: Int,
        val items: Map<String, Item>,
        val keyAbility: ValueType.StringListValue,
        val perception: Int,
        val publication: Publication,
        val rules: JsonElement,
        val savingThrows: SavingThrows,
        val skillFeatLevels: ValueType.IntListValue,
        val skillIncreaseLevels: ValueType.IntListValue,
        val trainedSkills: TrainedSkills,
        val traits: Traits,
    )

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
