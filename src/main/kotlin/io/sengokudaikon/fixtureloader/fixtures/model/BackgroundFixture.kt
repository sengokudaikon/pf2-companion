package io.sengokudaikon.fixtureloader.fixtures.model

import io.sengokudaikon.fixtureloader.fixtures.dto.JsonItem
import io.sengokudaikon.fixtureloader.fixtures.model.system.Publication
import io.sengokudaikon.fixtureloader.fixtures.model.system.Traits
import io.sengokudaikon.fixtureloader.fixtures.model.system.ValueType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Suppress("ConstructorParameterNaming")
@Serializable
data class BackgroundFixture(
    val _id: String,
    val img: String,
    val name: String,
    val type: String,
    val system: SystemProperty,
) {
    @Serializable
    data class SystemProperty(
        val boosts: Map<String, ValueType.StringListValue>,
        val description: ValueType.StringValue,
        val items: Map<String, JsonItem>,
        val rules: JsonElement,
        val publication: Publication,
        val trainedLore: String,
        val trainedSkills: ValueType.StringListValue,
        val traits: Traits,
    )
}
