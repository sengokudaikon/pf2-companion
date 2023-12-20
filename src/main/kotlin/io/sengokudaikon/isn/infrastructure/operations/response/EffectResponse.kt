package io.sengokudaikon.isn.infrastructure.operations.response

import io.sengokudaikon.isn.compendium.domain.effects.model.EffectDuration
import io.sengokudaikon.isn.compendium.domain.effects.model.EffectStart
import io.sengokudaikon.isn.compendium.domain.effects.model.EffectSystem
import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.domain.system.TokenIcon
import io.sengokudaikon.isn.infrastructure.domain.EffectModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class EffectResponse<T : EffectModel>(
    val id: String,
    val img: String,
    val name: String,
    @SerialName("type_")
    val type: String,
    val description: DescriptionType,
    val publication: Publication,
    val traits: TraitsResponse,
    val rules: JsonElement?,
    val badge: EffectSystem.Badge?,
    val duration: EffectDuration,
    val level: Int,
    val start: EffectStart,
    val tokenIcon: TokenIcon
) : Response<T>()
