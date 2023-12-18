package io.sengokudaikon.isn.chargen.adapters.character.read

import io.sengokudaikon.isn.chargen.domain.model.CharacterModel
import io.sengokudaikon.isn.chargen.operations.character.query.CharacterQuery
import io.sengokudaikon.isn.chargen.ports.ByNameCharacterPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject
@Single(binds = [QueryHandler::class])
class CharacterByNameHandler: ByNameHandler<CharacterModel, CharacterQuery.ByName, ByNameCharacterPort>() {
    override val useCase: ByNameCharacterPort by inject()

    override fun createQuery(name: String, id: String?): CharacterQuery.ByName {
        return CharacterQuery.ByName(name)
    }
}