package io.sengokudaikon.isn.compendium.infrastructure.mapper

import io.sengokudaikon.isn.compendium.domain.booncurse.BoonCurseModel
import io.sengokudaikon.isn.infrastructure.operations.Response

class BoonCurseMapper : Mapper<BoonCurseModel> {
    override fun toResponse(model: BoonCurseModel): Response<BoonCurseModel> {
        return with(model) {
            TODO()
        }
    }
}
