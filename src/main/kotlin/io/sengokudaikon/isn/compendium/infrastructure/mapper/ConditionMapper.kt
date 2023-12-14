package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.condition.ConditionModel
import io.sengokudaikon.isn.infrastructure.operations.Response

class ConditionMapper : Mapper<ConditionModel> {
    override fun toResponse(model: ConditionModel): Response<ConditionModel> {
        return with(model) {
            TODO()
        }
    }
}
