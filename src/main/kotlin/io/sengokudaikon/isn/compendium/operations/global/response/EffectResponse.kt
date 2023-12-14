package io.sengokudaikon.isn.compendium.operations.global.response

import io.sengokudaikon.isn.compendium.domain.effects.model.EffectDuration
import io.sengokudaikon.isn.compendium.domain.effects.model.EffectStart
import io.sengokudaikon.isn.compendium.domain.effects.model.EffectSystem
import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.domain.system.TokenIcon
import io.sengokudaikon.isn.infrastructure.domain.EffectModel
import io.sengokudaikon.isn.infrastructure.operations.Response
import kotlinx.serialization.Serializable

@Serializable
data class EffectResponse<T : EffectModel>(
    val id: String,
    val img: String,
    val name: String,
    val type: String,
    val description: DescriptionType,
    val publication: Publication,
    val traits: TraitsResponse,
    val rules: List<RuleResponse>,
    val badge: EffectSystem.Badge?,
    val duration: EffectDuration,
    val level: Int,
    val start: EffectStart,
    val tokenIcon: TokenIcon
) : Response<T>
