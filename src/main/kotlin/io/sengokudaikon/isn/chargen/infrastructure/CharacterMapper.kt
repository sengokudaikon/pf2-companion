package io.sengokudaikon.isn.chargen.infrastructure

import io.sengokudaikon.isn.chargen.domain.model.CharacterModel
import io.sengokudaikon.isn.compendium.infrastructure.mapper.Mapper
import io.sengokudaikon.isn.infrastructure.operations.response.Response

class CharacterMapper : Mapper<CharacterModel> {
    override fun toResponse(model: CharacterModel): Response<out CharacterModel> {
        TODO("Not yet implemented")
    }
}
