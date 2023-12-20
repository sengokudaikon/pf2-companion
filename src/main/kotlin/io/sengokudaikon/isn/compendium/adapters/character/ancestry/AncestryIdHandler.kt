package io.sengokudaikon.isn.compendium.adapters.character.ancestry

import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryModel
import io.sengokudaikon.isn.compendium.operations.character.ancestry.query.AncestryQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdAncestryPort
import io.sengokudaikon.isn.infrastructure.adapters.ByIdHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class AncestryIdHandler : ByIdHandler<AncestryModel, AncestryQuery.ById, ByIdAncestryPort>() {
    override val useCase: ByIdAncestryPort by inject()

    override fun createQuery(id: String, secondaryId: String?): AncestryQuery.ById {
        return AncestryQuery.ById(id)
    }
}
