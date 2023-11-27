package io.sengokudaikon.isn.builder.fixtures.model

import io.sengokudaikon.isn.builder.fixtures.model.system.Publication
import io.sengokudaikon.isn.builder.fixtures.model.system.Value
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
@Suppress("ConstructorParameterNaming")
data class ConditionFixture(
    val _id: String,
    val img: String,
    val name: String,
    val type: String,
    val system: SystemProperty,
) {
    @Serializable
    data class SystemProperty(
        val description: Value.String,
        val duration: Value.Int,
        val group: String,
        val overrides: List<String>,
        val publication: Publication,
        val references: References,
        val rules: JsonElement,
    ) {

        @Serializable
        data class References(
            val children: List<String>,
            val immunityFrom: List<String>,
            val overriddenBy: List<String>,
            val overrides: List<String>,
        )
    }
}
