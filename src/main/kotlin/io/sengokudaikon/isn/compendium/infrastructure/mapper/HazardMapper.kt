package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.hazard.HazardModel
import io.sengokudaikon.isn.infrastructure.operations.response.Response

class HazardMapper : Mapper<HazardModel> {
    override fun toResponse(model: HazardModel): Response<out HazardModel> {
        TODO("Not yet implemented")
    }
}
