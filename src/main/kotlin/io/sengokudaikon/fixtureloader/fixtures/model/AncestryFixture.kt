package io.sengokudaikon.fixtureloader.fixtures.model

import io.sengokudaikon.fixtureloader.fixtures.model.system.Item
import io.sengokudaikon.fixtureloader.fixtures.model.system.Languages
import io.sengokudaikon.fixtureloader.fixtures.model.system.Traits
import io.sengokudaikon.fixtureloader.fixtures.model.system.ValueType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Suppress("ConstructorParameterNaming")
@Serializable
data class AncestryFixture(
    val _id: String,
    val img: String,
    val name: String,
    val type: String,
    val system: SystemProperty,
) {
    @Serializable
    data class SystemProperty(
        val rarity: String,
        val additionalLanguages: Languages,
        val boosts: Map<String, ValueType.StringListValue>,
        val description: String,
        val flaws: Map<String, ValueType.StringListValue>,
        val hp: Int,
        val languages: Languages,
        val reach: Int,
        val rules: JsonElement,
        val size: String,
        val source: String,
        val speed: Int,
        val items: Map<String, Item>,
        val traits: Traits,
        val vision: String,
        val additionalSense: String?,
    )
}
