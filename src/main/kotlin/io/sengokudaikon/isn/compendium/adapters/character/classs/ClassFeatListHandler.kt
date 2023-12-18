package io.sengokudaikon.isn.compendium.adapters.character.classs

import io.sengokudaikon.isn.compendium.domain.classs.ClassFeatureModel
import io.sengokudaikon.isn.compendium.operations.character.classs.query.ClassFeatureQuery
import io.sengokudaikon.isn.compendium.ports.character.ListClassFeatPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class ClassFeatListHandler : ListHandler<List<ClassFeatureModel>, ClassFeatureQuery.All, ListClassFeatPort>() {
    override val useCase: ListClassFeatPort by inject()
    override fun createQuery(page: Int, size: Int, id: String?): ClassFeatureQuery.All {
        return ClassFeatureQuery.All(id!!, page, size)
    }
}
