package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.chargen.domain.model.AnimalCompanionModel
import io.sengokudaikon.isn.infrastructure.operations.response.Response

class AnimalCompanionMapper : Mapper<AnimalCompanionModel> {
    override fun toResponse(model: AnimalCompanionModel): Response<out AnimalCompanionModel> {
        TODO("Not yet implemented")
    }
}
