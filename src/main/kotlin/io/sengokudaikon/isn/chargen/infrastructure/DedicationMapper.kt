package io.sengokudaikon.isn.chargen.infrastructure

import io.sengokudaikon.isn.chargen.domain.model.DedicationModel
import io.sengokudaikon.isn.compendium.infrastructure.mapper.Mapper
import io.sengokudaikon.isn.infrastructure.operations.response.Response

class DedicationMapper : Mapper<DedicationModel> {
    override fun toResponse(model: DedicationModel): Response<out DedicationModel> {
        TODO("Not yet implemented")
    }
}
