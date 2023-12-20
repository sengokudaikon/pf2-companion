package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.equipment.model.EquipmentModel
import io.sengokudaikon.isn.infrastructure.operations.response.Response

class EquipmentMapper : Mapper<EquipmentModel> {
    override fun toResponse(model: EquipmentModel): Response<out EquipmentModel> {
        TODO("Not yet implemented")
    }
}
