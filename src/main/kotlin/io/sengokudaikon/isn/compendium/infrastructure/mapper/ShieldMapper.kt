package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.equipment.model.ShieldModel
import io.sengokudaikon.isn.infrastructure.operations.response.ShieldResponse
import io.sengokudaikon.isn.infrastructure.operations.transform
import kotlinx.serialization.json.JsonNull
import org.koin.core.annotation.Single

@Single
class ShieldMapper : Mapper<ShieldModel> {
    override fun toResponse(model: ShieldModel): ShieldResponse {
        return with(model) {
            ShieldResponse(
                id = id.toHexString(),
                img = img,
                name = name,
                type = type,
                rarity = system.traits.rarity,
                description = system.description.value,
                rules = system.rules?.let { rulesToJson(it.asArray()) } ?: JsonNull,
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
                acBonus = system.acBonus,
                bulk = system.bulk?.transform().extractValue().toString(),
                specific = system.specific,
                speedPenalty = system.speedPenalty,
                level = system.level.transform().extractValue().toString().toInt(),
            )
        }
    }
}
