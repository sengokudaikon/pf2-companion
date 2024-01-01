package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.equipment.model.ConsumableModel
import io.sengokudaikon.isn.infrastructure.operations.response.ConsumableResponse
import io.sengokudaikon.isn.infrastructure.operations.transform
import org.koin.core.annotation.Single

@Single
class ConsumableMapper: Mapper<ConsumableModel> {
    override fun toResponse(model: ConsumableModel): ConsumableResponse {
        return with(model) {
            ConsumableResponse(
                id = id.toString(),
                img = img,
                name = name,
                type = type,
                rarity = system.traits?.rarity,
                description = system.description.value,
                rules = system.rules?.let { rulesToJson(it.asArray()) },
                traits = system.traits?.value,
                publication = system.publication,
                price = system.price,
                quantity = system.quantity,
                bulk = system.bulk?.transform().extractValue().toString(),
                hardness = system.hardness,
                hp = system.hp,
                material = system.material,
                containerId = system.containerId,
                baseItem = system.baseItem,
                level = system.level.transform().extractValue()?.toString()?.toInt() ?: 0,
                size = system.size,
                usage = system.usage.transform().extractValue().toString(),
                uses = system.uses
            )
        }
    }
}
