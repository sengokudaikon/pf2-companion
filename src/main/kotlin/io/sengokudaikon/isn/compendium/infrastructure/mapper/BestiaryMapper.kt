package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.bestiary.BestiaryModel
import io.sengokudaikon.isn.infrastructure.operations.response.Response
import org.koin.core.annotation.Single

@Single
class BestiaryMapper : Mapper<BestiaryModel> {
    override fun toResponse(model: BestiaryModel): Response<BestiaryModel> {
        return with(model) {
            TODO()
        }
    }
}
