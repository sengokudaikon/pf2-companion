package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.equipment.model.ArmorModel
import io.sengokudaikon.isn.infrastructure.operations.response.ArmorResponse
import io.sengokudaikon.isn.infrastructure.operations.response.Response
import io.sengokudaikon.isn.infrastructure.operations.transform
import kotlinx.serialization.json.JsonNull
import org.koin.core.annotation.Single

@Single
class ArmorMapper : Mapper<ArmorModel> {
    override fun toResponse(model: ArmorModel): Response<ArmorModel> {
        return with(model) {
            ArmorResponse(
                id = id.toHexString(),
                img = img,
                name = name,
                type = type,
                description = system.description,
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
                stackGroup = system.stackGroup,
                group = system.group,
                resiliencyRune = system.resiliencyRune?.transform().extractValue(),
                specific = system.specific,
                speedPenalty = system.speedPenalty,
                acBonus = system.acBonus,
                dexCap = system.dexCap,
                category = system.category,
                checkPenalty = system.checkPenalty,
                strength = system.strength,
                equippedBulk = system.equippedBulk.transform().extractValue(),
                negateBulk = system.negateBulk.transform().extractValue(),
                level = system.level.transform().extractValue(),
                usage = system.usage.transform().extractValue(),
                weight = system.weight.transform().extractValue(),
                potency = system.potency?.transform().extractValue(),
                potencyRune = system.potencyRune.transform().extractValue(),
                propertyRune1 = system.propertyRune1?.transform().extractValue(),
                propertyRune2 = system.propertyRune2?.transform().extractValue(),
                propertyRune3 = system.propertyRune3?.transform().extractValue(),
                propertyRune4 = system.propertyRune4?.transform().extractValue(),
            )
        }
    }
}
