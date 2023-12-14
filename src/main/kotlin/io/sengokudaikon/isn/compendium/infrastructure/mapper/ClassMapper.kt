package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.classs.ClassModel
import io.sengokudaikon.isn.compendium.operations.character.classs.response.ClassResponse
import io.sengokudaikon.isn.infrastructure.operations.Response
import io.sengokudaikon.isn.infrastructure.operations.transform

class ClassMapper : Mapper<ClassModel> {
    override fun toResponse(model: ClassModel): Response<ClassModel> {
        return with(model) {
            ClassResponse(
                id = id.toHexString(),
                img = img,
                name = name,
                type = type,
                description = system.description,
                publication = system.publication,
                rules = system.rules.map { it.toResponse() },
                traits = system.traits.toResponse(),
                attacks = system.attacks,
                classDC = system.classDC,
                defenses = system.defenses,
                hp = system.hp,
                items = system.items.mapValues { it.value.toResponse() },
                perception = system.perception,
                savingThrows = system.savingThrows,
                trainedSkills = system.trainedSkills,
                skillFeatLevels = system.skillFeatLevels.transform(),
                skillIncreaseLevels = system.skillIncreaseLevels.transform(),
                ancestryFeatLevels = system.ancestryFeatLevels.transform(),
                classFeatLevels = system.classFeatLevels.transform(),
                generalFeatLevels = system.generalFeatLevels.transform(),
                keyAbility = system.keyAbility.transform(),
            )
        }
    }
}
