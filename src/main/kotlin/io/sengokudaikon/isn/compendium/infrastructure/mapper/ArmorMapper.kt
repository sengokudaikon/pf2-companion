package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.equipment.model.ArmorModel
import io.sengokudaikon.isn.infrastructure.operations.response.ArmorResponse
import io.sengokudaikon.isn.infrastructure.operations.transform
import kotlinx.serialization.json.JsonNull
import org.koin.core.annotation.Single

@Single
class ArmorMapper : Mapper<ArmorModel> {
    override fun toResponse(model: ArmorModel): ArmorResponse {
        return with(model) {
            ArmorResponse(
                id = id.toHexString(),
                img = img,
                name = name,
                type = type,
                rarity = system.traits.rarity,
                description = system.description.value,
                rules = system.rules?.let { rulesToJson(it.asArray()) } ?: JsonNull,
                traits = system.traits,
                publication = system.publication,
                baseItem = system.baseItem,
                containerId = system.containerId,
                hardness = system.hardness,
                hp = system.hp,
                material = system.material,
                price = system.price,
                quantity = system.quantity,
                size = system.size,
                group = system.group,
                specific = system.specific,
                speedPenalty = system.speedPenalty,
                acBonus = system.acBonus,
                dexCap = system.dexCap,
                category = system.category,
                checkPenalty = system.checkPenalty,
                strength = system.strength,
                level = system.level.transform().extractValue(),
                weight = system.bulk.transform().extractValue(),
            )
        }
    }
}
