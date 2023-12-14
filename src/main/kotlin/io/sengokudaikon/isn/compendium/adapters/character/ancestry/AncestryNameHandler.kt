package io.sengokudaikon.isn.compendium.adapters.character.ancestry

import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryModel
import io.sengokudaikon.isn.compendium.operations.character.ancestry.query.AncestryQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameAncestryPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class AncestryNameHandler : ByNameHandler<AncestryModel, AncestryQuery.ByName, ByNameAncestryPort>() {
    override val useCase: ByNameAncestryPort by inject()
    override fun createQuery(name: String, id: String?): AncestryQuery.ByName {
        return AncestryQuery.ByName(name)
    }
}
