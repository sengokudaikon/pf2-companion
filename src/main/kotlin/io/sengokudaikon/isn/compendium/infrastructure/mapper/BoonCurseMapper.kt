package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.booncurse.BoonCurseModel
import io.sengokudaikon.isn.infrastructure.operations.response.BoonCurseResponse
import io.sengokudaikon.isn.infrastructure.operations.response.Response
import org.koin.core.annotation.Single

@Single
class BoonCurseMapper : Mapper<BoonCurseModel> {
    override fun toResponse(model: BoonCurseModel): Response<BoonCurseModel> {
        return with(model) {
            BoonCurseResponse(
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
