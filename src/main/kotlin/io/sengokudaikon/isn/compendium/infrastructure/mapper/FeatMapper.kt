package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.infrastructure.operations.response.FeatureResponse
import io.sengokudaikon.isn.infrastructure.operations.response.Response
import io.sengokudaikon.isn.infrastructure.operations.transform
import org.koin.core.annotation.Single

@Single()
class FeatMapper : Mapper<FeatModel> {
    override fun toResponse(model: FeatModel): Response<FeatModel> {
        return with(model) {
            val response = FeatureResponse<FeatModel>(
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

            response
        }
    }
}
