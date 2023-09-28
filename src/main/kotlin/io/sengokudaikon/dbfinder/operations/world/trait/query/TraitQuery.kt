package io.sengokudaikon.dbfinder.operations.world.trait.query

import kotlinx.serialization.Serializable
import kotlinx.uuid.UUID

interface TraitQuery {
    @Serializable
    data class FindAll(val page: Int, val size: Int) : TraitQuery

    @Serializable
    data class FindById(val id: UUID) : TraitQuery

    @Serializable
    data class FindByName(val name: String) : TraitQuery
}
