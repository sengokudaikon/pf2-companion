package io.sengokudaikon.isn.builder.fixtures.model

import io.sengokudaikon.isn.builder.fixtures.model.system.Frequency
import io.sengokudaikon.isn.builder.fixtures.model.system.Publication
import io.sengokudaikon.isn.builder.fixtures.model.system.SelfEffect
import io.sengokudaikon.isn.builder.fixtures.model.system.Traits
import io.sengokudaikon.isn.builder.fixtures.model.system.Value
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Suppress("ConstructorParameterNaming")
data class ClassFeatureFixture(
    val _id: String,
    val img: String,
    val name: String,
    val type: String,
    val system: SystemProperty,
) {
    @Serializable
    data class SystemProperty(
        val actionType: Value.String,
        val actions: Value.Int?,
        val category: String,
        val description: Value.String,
        val frequency: Frequency,
        val publication: Publication,
        val selfEffect: SelfEffect?,
        val requirements: Value.String,
        val rules: JsonElement,
        val traits: Traits,
        val trigger: Value.String,
    )
}
