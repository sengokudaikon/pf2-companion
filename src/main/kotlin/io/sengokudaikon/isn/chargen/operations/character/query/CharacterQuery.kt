package io.sengokudaikon.isn.chargen.operations.character.query

import io.ktor.resources.*
import io.sengokudaikon.isn.AuthenticatedAPI
import io.sengokudaikon.isn.PrivateAPI
import io.sengokudaikon.isn.chargen.domain.model.CharacterModel
import io.sengokudaikon.isn.infrastructure.operations.Query

interface CharacterQuery : Query {
    @Resource("/api/character/list/{page}/{size}")
    @PrivateAPI
    data class All(
        override val page: Int,
        override val size: Int,
    ) : Query.All<List<CharacterModel>>, CharacterQuery

    @Resource("/api/character/{id}")
    @PrivateAPI
    data class ById(
        override val id: String,
    ) : Query.ById<CharacterModel>, CharacterQuery

    @Resource("/api/character/name/{name}")
    @PrivateAPI
    data class ByName(
        override val name: String,
    ) : Query.ByName<CharacterModel>, CharacterQuery

    @Resource("/api/user/characters")
    @AuthenticatedAPI
    data class ListByUser(
        override val page: Int,
        override val size: Int,
    ) : CharacterQuery, Query.All<List<CharacterModel>> {
        lateinit var userId: String
    }

    @Resource("/api/user/characters/{id}")
    @AuthenticatedAPI
    data class ByIdAndUser(
        override val id: String,
    ) : CharacterQuery, Query.ById<CharacterModel> {
        lateinit var userId: String
    }

    @Resource("/api/user/characters/name/{name}")
    @AuthenticatedAPI
    data class ByNameAndUser(
        override val name: String,
    ) : CharacterQuery, Query.ByName<CharacterModel> {
        lateinit var userId: String
    }
}
