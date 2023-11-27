package io.sengokudaikon.isn.builder.fixtures.model

import io.sengokudaikon.isn.builder.fixtures.model.system.Publication
import io.sengokudaikon.isn.builder.fixtures.model.system.Traits
import io.sengokudaikon.isn.builder.fixtures.model.system.Value
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
        val actionType: Value.String,
        val actions: Value.Int?,
        val category: String,
        val level: Value.Int,
        val prerequisites: Prerequisites,
        val description: Value.String,
        val publication: Publication,
        val traits: Traits,
        val rules: JsonElement,
    ) {
        @Serializable
        data class Prerequisites(
            val value: List<Value.String>,
        )
    }
}
