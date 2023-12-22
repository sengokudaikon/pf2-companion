package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.condition.ConditionModel
import io.sengokudaikon.isn.infrastructure.operations.response.ConditionResponse
import io.sengokudaikon.isn.infrastructure.operations.response.Response
import org.koin.core.annotation.Single

@Single
class ConditionMapper : Mapper<ConditionModel> {
    override fun toResponse(model: ConditionModel): Response<ConditionModel> {
        return with(model) {
            ConditionResponse(
                id = id.toHexString(),
                name = name,
                description = system.description.value,
                type = type,
                rarity = system.traits.rarity,
                traits = system.traits.value,
                publication = system.publication,
                rules = system.rules?.let { rulesToJson(it.asArray()) },
            )
        }
    }
}
