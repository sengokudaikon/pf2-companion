package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.equipment.model.KitModel
import io.sengokudaikon.isn.infrastructure.operations.response.KitResponse
import io.sengokudaikon.isn.infrastructure.operations.transform
import org.koin.core.annotation.Single

@Single
class KitMapper: Mapper<KitModel> {
    override fun toResponse(model: KitModel): KitResponse {
        return with(model) {
            KitResponse(
                id = id.toString(),
                name = name,
                img = img,
                type = type,
                price = system.price,
                description = system.description.value,
                items = system.items?.transform().extractValue(),
                publication = system.publication,
                rules = system.rules?.let { rulesToJson(it.asArray()) },
                traits = system.traits?.value
            )
        }
    }
}
