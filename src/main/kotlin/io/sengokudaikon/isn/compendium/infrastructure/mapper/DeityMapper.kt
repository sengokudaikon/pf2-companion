package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.deity.DeityModel
import io.sengokudaikon.isn.infrastructure.operations.Response

class DeityMapper : Mapper<DeityModel> {
    override fun toResponse(model: DeityModel): Response<DeityModel> {
        return with(model) {
            TODO()
        }
    }
}
