package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.bestiary.BestiaryEffectModel
import io.sengokudaikon.isn.infrastructure.operations.response.EffectResponse
import io.sengokudaikon.isn.infrastructure.operations.response.Response
import io.sengokudaikon.isn.infrastructure.operations.transform
import org.koin.core.annotation.Single

@Single
class BestiaryEffectMapper : Mapper<BestiaryEffectModel> {
    override fun toResponse(model: BestiaryEffectModel): Response<BestiaryEffectModel> {
        return with(model) {
            EffectResponse(
                id = id.toString(),
                img = img,
                name = name,
                type = type,
                rarity = system.traits.rarity,
                description = system.description.value,
                publication = system.publication,
                traits = system.traits.value,
                rules = system.rules?.let { rulesToJson(it.asArray()) },
                badge = system.badge?.transform().extractValue(),
                duration = system.duration?.transform().extractValue(),
                level = system.level.transform().extractValue()?.toString()?.toInt() ?: 0,
                start = system.start?.transform().extractValue(),
                tokenIcon = system.tokenIcon,
            )
        }
    }
}
