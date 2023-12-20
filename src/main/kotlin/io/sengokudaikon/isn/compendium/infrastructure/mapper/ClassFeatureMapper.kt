package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.classs.ClassFeatureModel
import io.sengokudaikon.isn.compendium.enums.ActionTypes
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
                description = system.description,
                publication = system.publication,
                traits = system.traits.toResponse(),
                rules = system.rules?.let { rulesToJson(it.asArray()) },
                actionType = ActionTypes.fromString(
                    system.actionType?.transform().extractValue()?.toString()?.uppercase() ?: ActionTypes.NONE.name,
                ),
                actions = system.actions?.transform().extractValue(),
                category = system.category,
                level = system.level?.transform().extractValue()?.toString()?.toInt(),
                prerequisites = system.prerequisites?.transform().extractValue(),
            )
        }
    }
}
