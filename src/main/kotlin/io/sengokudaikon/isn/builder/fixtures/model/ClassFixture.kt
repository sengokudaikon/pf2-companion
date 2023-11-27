package io.sengokudaikon.isn.builder.fixtures.model

import io.sengokudaikon.isn.builder.fixtures.model.system.Item
import io.sengokudaikon.isn.builder.fixtures.model.system.Publication
import io.sengokudaikon.isn.builder.fixtures.model.system.Traits
import io.sengokudaikon.isn.builder.fixtures.model.system.Value
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
        val ancestryFeatLevels: Value.IntList,
        val attacks: Attacks,
        val classDC: Int,
        val classFeatLevels: Value.IntList,
        val defenses: Defenses,
        val description: Value.String,
        val generalFeatLevels: Value.IntList,
        val hp: Int,
        val items: Map<String, Item>,
        val keyAbility: Value.StringList,
        val perception: Int,
        val publication: Publication,
        val rules: JsonElement,
        val savingThrows: SavingThrows,
        val skillFeatLevels: Value.IntList,
        val skillIncreaseLevels: Value.IntList,
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
