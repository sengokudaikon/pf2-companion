package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.classs.ClassModel
import io.sengokudaikon.isn.infrastructure.operations.response.ClassResponse
import io.sengokudaikon.isn.infrastructure.operations.transform
import org.koin.core.annotation.Single

@Single
class ClassMapper(
    private val classFeatMapper: ClassFeatureMapper,
) : Mapper<ClassModel> {
    override fun toResponse(model: ClassModel): ClassResponse {
        return with(model) {
            ClassResponse(
                id = id.toHexString(),
                img = img,
                name = name,
                type = type,
                rarity = system.traits.rarity,
                description = system.description.value,
                publication = system.publication,
                rules = system.rules?.let { rulesToJson(it.asArray()) },
                traits = system.traits.value,
                attacks = system.attacks,
                defenses = system.defenses,
                hp = system.hp,
                perception = system.perception,
                savingThrows = system.savingThrows,
                trainedSkills = system.trainedSkills,
                features = features.map { classFeatMapper.toResponse(it) },
                skillFeatLevels = system.skillFeatLevels.transform().extractValue(),
                skillIncreaseLevels = system.skillIncreaseLevels.transform().extractValue(),
                ancestryFeatLevels = system.ancestryFeatLevels.transform().extractValue(),
                classFeatLevels = system.classFeatLevels.transform().extractValue(),
                generalFeatLevels = system.generalFeatLevels.transform().extractValue(),
                keyAbility = system.keyAbility.transform().extractValue(),
            )
        }
    }
}
