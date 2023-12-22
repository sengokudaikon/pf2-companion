package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.heritage.HeritageModel
import io.sengokudaikon.isn.infrastructure.operations.response.HeritageResponse
import org.koin.core.annotation.Single

@Single
class HeritageMapper : Mapper<HeritageModel> {
    override fun toResponse(model: HeritageModel): HeritageResponse {
        with(model) {
            return HeritageResponse(
                id = id.toHexString(),
                name = name,
                type = type,
                description = system.description.value,
                rarity = system.traits?.rarity,
                ancestryId = system.ancestry.name,
                publication = system.publication,
                traits = system.traits?.value,
                rules = system.rules?.let { rulesToJson(it.asArray()) },
            )
        }
    }
}
