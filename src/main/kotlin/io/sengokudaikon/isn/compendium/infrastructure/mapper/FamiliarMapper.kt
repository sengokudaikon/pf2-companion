package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.chargen.domain.model.FamiliarModel
import io.sengokudaikon.isn.infrastructure.operations.response.Response

class FamiliarMapper : Mapper<FamiliarModel> {
    override fun toResponse(model: FamiliarModel): Response<out FamiliarModel> {
        TODO()
    }
}
