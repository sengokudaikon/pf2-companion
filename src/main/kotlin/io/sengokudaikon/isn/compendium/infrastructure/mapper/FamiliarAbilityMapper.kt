package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.familiar.FamiliarAbilityModel
import io.sengokudaikon.isn.infrastructure.operations.response.FeatureResponse
import io.sengokudaikon.isn.infrastructure.operations.transform
import org.koin.core.annotation.Single

@Single
class FamiliarAbilityMapper : Mapper<FamiliarAbilityModel> {
    override fun toResponse(model: FamiliarAbilityModel): FeatureResponse<FamiliarAbilityModel> {
        return with(model) {
            FeatureResponse(
                id = id.toString(),
                img = img,
                name = name,
                type = type,
                description = system.description.value,
                publication = system.publication,
                traits = system.traits.value,
                rules = system.rules?.let { rulesToJson(it.asArray()) },
                frequency = system.frequency,
                isDefault = system.isDefault?.transform(),
                actionType = system.actionType?.transform().extractValue(),
                actions = system.actions?.transform().extractValue(),
                category = system.category,
                level = system.level?.transform().extractValue()?.toString()?.toInt(),
                maxTakable = system.maxTakable?.transform(),
                prerequisites = system.prerequisites?.transform().extractValue(),
                trigger = system.trigger?.transform(),
                effects = null,
                rarity = system.traits.rarity,
            )
        }
    }
}
