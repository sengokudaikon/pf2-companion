package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.deity.DeityModel
import io.sengokudaikon.isn.infrastructure.operations.response.DeityResponse
import io.sengokudaikon.isn.infrastructure.operations.response.Response
import org.koin.core.annotation.Single

@Single()
class DeityMapper : Mapper<DeityModel> {

    override fun toResponse(model: DeityModel): Response<DeityModel> {
        return with(model) {
            DeityResponse(
                id = id.toHexString(),
                name = name,
                description = system.description.value,
                type = type,
                rarity = system.traits?.rarity,
                traits = system.traits?.value,
                publication = system.publication,
                rules = system.rules?.let { rulesToJson(it.asArray()) },
            )
        }
    }
}
