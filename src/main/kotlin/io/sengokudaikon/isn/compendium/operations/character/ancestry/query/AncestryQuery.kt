package io.sengokudaikon.isn.compendium.operations.character.ancestry.query

import kotlinx.serialization.Serializable

interface AncestryQuery {
    @Serializable
    data class FindAll(val page: Int, val limit: Int) : AncestryQuery

    @Serializable
    data class FindById(val id: String) : AncestryQuery

    @Serializable
    data class FindByName(val name: String) : AncestryQuery
}
