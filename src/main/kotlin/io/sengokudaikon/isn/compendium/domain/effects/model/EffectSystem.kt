package io.sengokudaikon.isn.compendium.domain.effects.model

import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.GenericRule
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.domain.system.SystemModel
import io.sengokudaikon.isn.compendium.domain.system.TokenIcon
import io.sengokudaikon.isn.compendium.domain.system.Traits
import io.sengokudaikon.isn.compendium.domain.system.Value
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class EffectSystem(
    override val description: DescriptionType,
    override val publication: Publication,
    override val traits: Traits,
    override val rules: List<GenericRule>,
    val badge: Badge? = null,
    val duration: EffectDuration,
    val level: JsonObject,
    val start: EffectStart,
    val tokenIcon: TokenIcon,
) : SystemModel {
    @Serializable
    data class Badge(
        val max: Int? = null,
        val min: Int? = null,
        val evaluate: Boolean? = null,
        val reevaluate: String? = null,
        val labels: List<String> = listOf(),
        val type: String? = null,
        @Serializable val value: Value,
    )
}
