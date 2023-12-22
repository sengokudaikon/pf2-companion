package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.classs.ClassFeatureModel
import io.sengokudaikon.isn.infrastructure.operations.response.ClassFeatureResponse
import io.sengokudaikon.isn.infrastructure.operations.transform
import org.koin.core.annotation.Single

@Single
class ClassFeatureMapper : Mapper<ClassFeatureModel> {
    override fun toResponse(model: ClassFeatureModel): ClassFeatureResponse {
        return with(model) {
            ClassFeatureResponse(
                id = id.toString(),
                img = img,
                name = name,
                type = type,
                rarity = system.traits.rarity,
                description = system.description.value,
                publication = system.publication,
                traits = system.traits.value,
                rules = system.rules?.let { rulesToJson(it.asArray()) },
                actionType = system.actionType?.transform().extractValue(),
                actions = system.actions?.transform().extractValue(),
                category = system.category,
                level = system.level?.transform().extractValue()?.toString()?.toInt(),
                prerequisites = system.prerequisites?.transform().extractValue(),
            )
        }
    }
}
