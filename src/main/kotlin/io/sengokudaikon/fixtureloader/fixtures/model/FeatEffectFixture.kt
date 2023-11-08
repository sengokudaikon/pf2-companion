package io.sengokudaikon.fixtureloader.fixtures.model

import io.sengokudaikon.fixtureloader.fixtures.model.system.Publication
import io.sengokudaikon.fixtureloader.fixtures.model.system.Traits
import io.sengokudaikon.fixtureloader.fixtures.model.system.ValueType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Suppress("ConstructorParameterNaming")
@Serializable
data class FeatEffectFixture(
    val _id: String,
    val img: String,
    val name: String,
    val type: String,
    val system: SystemProperty,
) {

    @Serializable
    data class SystemProperty(
        val description: ValueType.StringValue,
        val duration: EffectDuration,
        val level: ValueType.IntValue,
        val publication: Publication,
        val rules: JsonElement,
        val start: EffectStart,
        val tokenIcon: TokenIcon,
        val traits: Traits,
    )

    @Serializable
    data class EffectStart(
        val initiative: String? = null,
        val value: Int,
    )

    @Serializable
    data class EffectDuration(
        val expiry: String,
        val sustained: Boolean,
        val unit: String,
        val value: String,
    )

    @Serializable
    data class TokenIcon(
        val show: Boolean,
    )
}
