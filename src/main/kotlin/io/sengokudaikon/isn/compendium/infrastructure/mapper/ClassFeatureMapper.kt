package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.classs.ClassFeatureModel
import io.sengokudaikon.isn.compendium.enums.ActionTypes
import io.sengokudaikon.isn.compendium.operations.global.response.FeatureResponse
import io.sengokudaikon.isn.infrastructure.operations.transform

class ClassFeatureMapper : Mapper<ClassFeatureModel> {
    override fun toResponse(model: ClassFeatureModel): FeatureResponse<ClassFeatureModel> {
        return with(model) {
            FeatureResponse(
                id = id.toString(),
                img = img,
                name = name,
                type = type,
                description = system.description,
                publication = system.publication,
                traits = system.traits.toResponse(),
                rules = system.rules.map { it.toResponse() },
                frequency = system.frequency,
                isDefault = system.isDefault?.transform(),
                actionType = ActionTypes.valueOf(
                    system.actionType?.transform().extractValue()?.uppercase() ?: ActionTypes.NONE.name,
                ),
                actions = system.actions?.transform().extractValue(),
                category = system.category,
                level = system.level?.transform().extractValue()?.toInt(),
                maxTakable = system.maxTakable?.transform(),
                prerequisites = system.prerequisites?.transform().extractValue(),
                trigger = system.trigger?.transform(),
                effects = null,
            )
        }
    }
}
