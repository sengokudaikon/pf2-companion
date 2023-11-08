package io.sengokudaikon.fixtureloader.fixtures.model

import io.sengokudaikon.fixtureloader.fixtures.model.system.Frequency
import io.sengokudaikon.fixtureloader.fixtures.model.system.Publication
import io.sengokudaikon.fixtureloader.fixtures.model.system.SelfEffect
import io.sengokudaikon.fixtureloader.fixtures.model.system.Traits
import io.sengokudaikon.fixtureloader.fixtures.model.system.ValueType
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
        val actionType: ValueType.StringValue,
        val actions: ValueType.IntValue?,
        val category: String,
        val description: ValueType.StringValue,
        val frequency: Frequency,
        val publication: Publication,
        val selfEffect: SelfEffect?,
        val requirements: ValueType.StringValue,
        val rules: JsonElement,
        val traits: Traits,
        val trigger: ValueType.StringValue,
    )
}
