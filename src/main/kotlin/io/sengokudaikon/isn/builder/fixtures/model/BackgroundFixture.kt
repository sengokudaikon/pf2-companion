package io.sengokudaikon.isn.builder.fixtures.model

import io.sengokudaikon.isn.builder.fixtures.dto.JsonItem
import io.sengokudaikon.isn.builder.fixtures.model.system.Publication
import io.sengokudaikon.isn.builder.fixtures.model.system.Traits
import io.sengokudaikon.isn.builder.fixtures.model.system.Value
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
        val boosts: Map<String, Value.StringList>,
        val description: Value.String,
        val items: Map<String, JsonItem>,
        val rules: JsonElement,
        val publication: Publication,
        val trainedLore: String,
        val trainedSkills: Value.StringList,
        val traits: Traits,
    )
}
