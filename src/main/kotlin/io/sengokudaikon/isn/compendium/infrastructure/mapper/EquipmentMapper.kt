package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.equipment.model.EquipmentModel
import io.sengokudaikon.isn.infrastructure.operations.response.EquipmentResponse
import io.sengokudaikon.isn.infrastructure.operations.transform
import org.koin.core.annotation.Single

@Single
class EquipmentMapper : Mapper<EquipmentModel> {
    override fun toResponse(model: EquipmentModel): EquipmentResponse {
        return with(model) {
            EquipmentResponse(
                id = id.toString(),
                img = img,
                name = name,
                type = type,
                description = system.description.value,
                rules = system.rules?.let { rulesToJson(it.asArray()) },
                rarity = system.traits.rarity,
                traits = system.traits.value,
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
                bulk = system.bulk.transform()?.extractValue()?.toString(),
                usage = system.usage.transform()?.extractValue()?.toString(),
            )
        }
    }
}
