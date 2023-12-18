package io.sengokudaikon.isn.chargen.adapters.character.read

import io.sengokudaikon.isn.chargen.domain.model.CharacterModel
import io.sengokudaikon.isn.chargen.operations.character.query.CharacterQuery
import io.sengokudaikon.isn.chargen.ports.ListAllCharactersPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject
@Single(binds = [QueryHandler::class])
class CharacterListDebugHandler: ListHandler<List<CharacterModel>, CharacterQuery.All, ListAllCharactersPort>() {
    override val useCase: ListAllCharactersPort by inject()
    override fun createQuery(page: Int, size: Int, id: String?): CharacterQuery.All {
        return CharacterQuery.All(page, size)
    }
}