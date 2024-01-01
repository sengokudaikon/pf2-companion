package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.spell.SpellModel
import io.sengokudaikon.isn.infrastructure.operations.response.SpellResponse
import io.sengokudaikon.isn.infrastructure.operations.transform
import org.koin.core.annotation.Single

@Single
class SpellMapper: Mapper<SpellModel> {
    override fun toResponse(model: SpellModel): SpellResponse {
        return with(model) {
            SpellResponse(
                id = id.toString(),
                img = img,
                name = name,
                type = type,
                description = system.description.value,
                publication = system.publication,
                traits = system.traits?.value,
                rarity = system.traits?.rarity,
                rules = system.rules?.let { rulesToJson(it.asArray()) },
                area = system.area?.transform().extractValue(),
                cost = system.cost?.transform().extractValue(),
                counteraction = system.counteraction,
                damage = system.damage?.transform().extractValue(),
                defense = system.defense?.transform().extractValue(),
                duration = system.duration.transform().extractValue(),
                level = system.level.transform().extractValue()?.toString()?.toInt(),
                range = system.range.transform().extractValue(),
                requirements = system.requirements,
                target = system.target.transform().extractValue(),
                time = system.time.transform().extractValue(),
                heightening = system.heightening?.transform().extractValue(),
                overlays = system.overlays?.transform().extractValue(),
            )
        }
    }
}
