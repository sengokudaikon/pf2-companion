package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.kingmaker.ArmyConditionModel
import io.sengokudaikon.isn.infrastructure.operations.response.Response

class ArmyConditionMapper : Mapper<ArmyConditionModel> {
    override fun toResponse(model: ArmyConditionModel): Response<out ArmyConditionModel> {
        TODO("Not yet implemented")
    }
}
