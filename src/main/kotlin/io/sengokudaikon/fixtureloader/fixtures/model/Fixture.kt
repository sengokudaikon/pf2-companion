package io.sengokudaikon.fixtureloader.fixtures.model

import io.sengokudaikon.fixtureloader.fixtures.model.system.GenericRule
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
open class Fixture(
    open val id: String,
    open val img: String,
    open val name: String,
    open val system: FixtureSystem,
)

@Serializable
open class FixtureSystem(
    open val description: String,
    open val traits: List<String>,
    open val rules: List<JsonElement>,
)

fun List<GenericRule.Predicate>.mapToString(): String {
    return this.joinToString(" ") { it.toString() }
}
