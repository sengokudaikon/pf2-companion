package io.sengokudaikon.dbfinder.fixtures

import kotlinx.serialization.Serializable

@Serializable
data class JsonTrait(
    val name: String,
    val description: String,
    val source: String,
    val type: String,
) {
    fun toFixture(): TraitFixture {
        return TraitFixture(
            name = name,
            description = description,
            contentSrc = source,
            type = type,
        )
    }
}
