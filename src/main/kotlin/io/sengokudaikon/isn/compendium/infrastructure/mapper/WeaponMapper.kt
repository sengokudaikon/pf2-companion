package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.equipment.model.WeaponModel
import io.sengokudaikon.isn.infrastructure.operations.response.Response
import io.sengokudaikon.isn.infrastructure.operations.response.WeaponResponse
import io.sengokudaikon.isn.infrastructure.operations.transform
import org.koin.core.annotation.Single

@Single
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
                rarity = system.traits.rarity,
                description = system.description.value,
                rules = system.rules?.let { rulesToJson(it.asArray()) },
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
                range = system.range,
                level = system.level.transform().extractValue().toString(),
                weight = system.bulk.transform().extractValue().toString(),
                reload = system.reload?.transform().extractValue().toString(),
                bonus = system.bonus.transform().extractValue().toString(),
                damage = system.damage,
                bonusDamage = system.bonusDamage.transform().extractValue().toString(),
            )
        }
    }
}
