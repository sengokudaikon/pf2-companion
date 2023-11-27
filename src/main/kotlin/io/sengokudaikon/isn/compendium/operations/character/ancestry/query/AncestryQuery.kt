package io.sengokudaikon.isn.compendium.operations.character.ancestry.query

import kotlinx.serialization.Serializable
import kotlinx.uuid.UUID

interface AncestryQuery {
    @Serializable
    data class FindAll(val page: Int, val limit: Int) : AncestryQuery

    @Serializable
    data class FindById(val id: UUID) : AncestryQuery

    @Serializable
    data class FindByName(val name: String) : AncestryQuery
}
