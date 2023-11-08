package io.sengokudaikon.fixtureloader.fixtures.model

import io.sengokudaikon.fixtureloader.fixtures.model.system.Publication
import io.sengokudaikon.fixtureloader.fixtures.model.system.Traits
import io.sengokudaikon.fixtureloader.fixtures.model.system.ValueType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Suppress("ConstructorParameterNaming")
@Serializable
data class AncestryFeatureFixture(
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
        val level: ValueType.IntValue,
        val prerequisites: Prerequisites,
        val description: ValueType.StringValue,
        val publication: Publication,
        val traits: Traits,
        val rules: JsonElement,
    ) {
        @Serializable
        data class Prerequisites(
            val value: List<ValueType.StringValue>,
        )
    }
}
