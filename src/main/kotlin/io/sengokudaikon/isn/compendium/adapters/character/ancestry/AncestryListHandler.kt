package io.sengokudaikon.isn.compendium.adapters.character.ancestry

import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryModel
import io.sengokudaikon.isn.compendium.operations.character.ancestry.query.AncestryQuery
import io.sengokudaikon.isn.compendium.ports.character.ListAncestryPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class AncestryListHandler : ListHandler<List<AncestryModel>, AncestryQuery.All, ListAncestryPort>() {
    override val useCase: ListAncestryPort by inject()
    override fun createQuery(page: Int, size: Int, id: String?): AncestryQuery.All {
        return AncestryQuery.All(page, size)
    }
}
