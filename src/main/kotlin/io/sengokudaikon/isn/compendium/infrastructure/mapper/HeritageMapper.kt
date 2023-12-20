package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.heritage.HeritageModel
import io.sengokudaikon.isn.infrastructure.operations.response.Response

class HeritageMapper : Mapper<HeritageModel> {
    override fun toResponse(model: HeritageModel): Response<out HeritageModel> {
        TODO("Not yet implemented")
    }
}
