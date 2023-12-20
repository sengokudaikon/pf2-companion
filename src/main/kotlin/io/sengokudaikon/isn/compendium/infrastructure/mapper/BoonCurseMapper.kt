package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.booncurse.BoonCurseModel
import io.sengokudaikon.isn.infrastructure.operations.response.Response
import org.koin.core.annotation.Single

@Single
class BoonCurseMapper : Mapper<BoonCurseModel> {
    override fun toResponse(model: BoonCurseModel): Response<BoonCurseModel> {
        return with(model) {
            TODO()
        }
    }
}
