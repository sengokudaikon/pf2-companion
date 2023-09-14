package io.sengokudaikon.dbfinder.operations.character.ancestry.query

import io.sengokudaikon.dbfinder.domain.character.ancestry.model.Ancestry
import io.sengokudaikon.shared.operations.Query
import kotlinx.serialization.Serializable
import kotlinx.uuid.UUID

interface AncestryQuery : Query<Ancestry> {
    @Serializable
    data class FindAll(val page: Int, val size: Int) : AncestryQuery

    @Serializable
    data class FindById(val id: UUID) : AncestryQuery

    @Serializable
    data class FindByName(val name: String) : AncestryQuery
}
