package io.sengokudaikon.isn.chargen.adapters.character.read

import io.sengokudaikon.isn.chargen.domain.model.CharacterModel
import io.sengokudaikon.isn.chargen.operations.character.query.CharacterQuery
import io.sengokudaikon.isn.chargen.ports.ByIdCharacterPort
import io.sengokudaikon.isn.infrastructure.adapters.ByIdHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class CharacterByIdHandler : ByIdHandler<CharacterModel, CharacterQuery.ById, ByIdCharacterPort>() {
    override val useCase: ByIdCharacterPort by inject()
    override fun createQuery(id: String, secondaryId: String?): CharacterQuery.ById {
        return CharacterQuery.ById(id)
    }
}
