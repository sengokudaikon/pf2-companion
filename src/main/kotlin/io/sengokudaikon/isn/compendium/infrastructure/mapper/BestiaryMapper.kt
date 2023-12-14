package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.bestiary.BestiaryModel
import io.sengokudaikon.isn.infrastructure.operations.Response

class BestiaryMapper : Mapper<BestiaryModel> {
    override fun toResponse(model: BestiaryModel): Response<BestiaryModel> {
        return with(model) {
            TODO()
        }
    }
}
