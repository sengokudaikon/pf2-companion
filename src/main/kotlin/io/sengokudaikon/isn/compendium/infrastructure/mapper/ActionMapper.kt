package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.action.ActionModel
import io.sengokudaikon.isn.infrastructure.operations.response.ActionResponse
import io.sengokudaikon.isn.infrastructure.operations.transform
import org.koin.core.annotation.Single

@Single
class ActionMapper(
    private val featEffectMapper: FeatEffectMapper
) : Mapper<ActionModel> {
    override fun toResponse(model: ActionModel): ActionResponse {
        return with(model) {
            ActionResponse(
                id = id.toString(),
                img = img,
                name = name,
                type = type,
                rarity = system.traits.rarity,
                description = system.description.value,
                publication = system.publication,
                traits = system.traits.value,
                rules = system.rules?.let { rulesToJson(it.asArray()) },
                selfEffect = effect?.let { featEffectMapper.toResponse(it) },
                actionType = system.actionType?.transform().extractValue(),
                actions = system.actions?.transform().extractValue(),
                category = system.category,
                trigger = system.trigger?.transform().extractValue(),
                weapon = system.weapon?.transform().extractValue(),
            )
        }
    }
}
