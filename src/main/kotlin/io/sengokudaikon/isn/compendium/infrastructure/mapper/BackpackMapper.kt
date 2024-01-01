package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.equipment.model.BackpackModel
import io.sengokudaikon.isn.infrastructure.operations.response.BackpackResponse
import io.sengokudaikon.isn.infrastructure.operations.transform
import org.koin.core.annotation.Single

@Single
class BackpackMapper: Mapper<BackpackModel> {
    override fun toResponse(model: BackpackModel): BackpackResponse {
        return with(model) {
            BackpackResponse(
                id = id.toString(),
                img = img,
                name = name,
                type = type,
                description = system.description.value,
                rules = system.rules?.let { rulesToJson(it.asArray()) },
                rarity = system.traits?.rarity,
                traits = system.traits?.value,
                publication = system.publication,
                baseItem = system.baseItem,
                containerId = system.containerId,
                hardness = system.hardness,
                hp = system.hp,
                material = system.material,
                price = system.price,
                quantity = system.quantity,
                size = system.size,
                level = system.level.transform().extractValue().toString().toInt(),
                bulk = system.bulk?.transform()?.extractValue()?.toString(),
                collapsed = system.collapsed,
                stowing = system.stowing
            )
        }
    }
}
