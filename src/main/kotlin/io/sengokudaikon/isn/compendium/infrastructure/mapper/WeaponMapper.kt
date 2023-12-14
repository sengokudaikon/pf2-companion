package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.equipment.model.WeaponModel
import io.sengokudaikon.isn.compendium.operations.character.equipment.response.WeaponResponse
import io.sengokudaikon.isn.infrastructure.operations.Response

class WeaponMapper : Mapper<WeaponModel> {
    override fun toResponse(model: WeaponModel): Response<WeaponModel> {
        return with(model) {
            WeaponResponse(
                id = id.toString(),
                img = img,
                name = name,
                type = type,
                group = system.group,
                specific = system.specific,
                potencyRune = system.potencyRune.toString(),
                propertyRune1 = system.propertyRune1.toString(),
                propertyRune2 = system.propertyRune2.toString(),
                propertyRune3 = system.propertyRune3.toString(),
                propertyRune4 = system.propertyRune4.toString(),
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
                equippedBulk = system.equippedBulk.toString(),
                negateBulk = system.negateBulk.toString(),
                level = system.level.toString(),
                usage = system.usage.toString(),
                weight = system.weight.toString(),
                reload = system.reload.toString(),
                bonus = system.bonus.toString(),
                strikingRune = system.strikingRune.toString(),
                damage = system.damage,
                bonusDamage = system.bonusDamage.toString(),
            )
        }
    }
}
