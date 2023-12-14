package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.equipment.model.ArmorModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.response.ArmorResponse
import io.sengokudaikon.isn.infrastructure.operations.Response
import io.sengokudaikon.isn.infrastructure.operations.transform

class ArmorMapper : Mapper<ArmorModel> {
    override fun toResponse(model: ArmorModel): Response<ArmorModel> {
        return with(model) {
            ArmorResponse(
                id = id.toHexString(),
                img = img,
                name = name,
                type = type,
                description = system.description,
                rules = system.rules.map { it.toResponse() },
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
                stackGroup = system.stackGroup,
                group = system.group,
                resiliencyRune = system.resiliencyRune?.transform().toString(),
                specific = system.specific,
                speedPenalty = system.speedPenalty,
                acBonus = system.acBonus,
                dexCap = system.dexCap,
                category = system.category,
                checkPenalty = system.checkPenalty,
                strength = system.strength,
                equippedBulk = system.equippedBulk.transform().toString(),
                negateBulk = system.negateBulk.transform().toString(),
                level = system.level.transform().toString(),
                usage = system.usage.transform().toString(),
                weight = system.weight.transform().toString(),
                potency = system.potency?.transform().toString(),
                potencyRune = system.potencyRune.transform().toString(),
                propertyRune1 = system.propertyRune1?.transform().toString(),
                propertyRune2 = system.propertyRune2?.transform().toString(),
                propertyRune3 = system.propertyRune3?.transform().toString(),
                propertyRune4 = system.propertyRune4?.transform().toString(),
            )
        }
    }
}
