package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.bestiary.BestiaryEffectModel
import io.sengokudaikon.isn.compendium.operations.global.response.EffectResponse
import io.sengokudaikon.isn.infrastructure.operations.Response
import io.sengokudaikon.isn.infrastructure.operations.transform

class BestiaryEffectMapper : Mapper<BestiaryEffectModel> {
    override fun toResponse(model: BestiaryEffectModel): Response<BestiaryEffectModel> {
        return with(model) {
            EffectResponse(
                id = id.toString(),
                img = img,
                name = name,
                type = type,
                description = system.description,
                publication = system.publication,
                traits = system.traits.toResponse(),
                rules = system.rules.map { it.toResponse() },
                badge = system.badge,
                duration = system.duration,
                level = system.level.transform().extractValue()?.toInt() ?: 0,
                start = system.start,
                tokenIcon = system.tokenIcon,
            )
        }
    }
}
