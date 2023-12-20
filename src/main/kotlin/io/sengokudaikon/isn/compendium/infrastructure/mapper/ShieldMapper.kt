package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.equipment.model.ShieldModel
import io.sengokudaikon.isn.infrastructure.operations.response.Response
import io.sengokudaikon.isn.infrastructure.operations.response.ShieldResponse
import io.sengokudaikon.isn.infrastructure.operations.transform
import kotlinx.serialization.json.JsonNull

class ShieldMapper : Mapper<ShieldModel> {
    override fun toResponse(model: ShieldModel): Response<out ShieldModel> {
        return with(model) {
            ShieldResponse(
                id = id.toHexString(),
                img = img,
                name = name,
                type = type,
                description = system.description,
                rules = system.rules?.let { rulesToJson(it.asArray()) } ?: JsonNull,
                traits = system.traits.toResponse(),
                publication = system.publication,
                baseItem = system.baseItem,
                containerId = system.containerId,
                hardness = system.hardness,
                hp = system.hp,
                material = system.material,
                price = system.price,
                quantity = system.quantity,
                size = system.size,
                stackGroup = system.stackGroup,
                acBonus = system.acBonus,
                bulk = system.bulk,
                specific = system.specific,
                speedPenalty = system.speedPenalty,
                level = system.level.transform().extractValue().toString().toInt(),
                usage = system.usage.transform().extractValue(),
            )
        }
    }
}
