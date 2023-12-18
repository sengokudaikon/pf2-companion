package io.sengokudaikon.isn.compendium.adapters.character.classs

import io.sengokudaikon.isn.compendium.domain.classs.ClassModel
import io.sengokudaikon.isn.compendium.operations.character.classs.query.ClassQuery
import io.sengokudaikon.isn.compendium.ports.character.ListClassPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class ClassListHandler :
    ListHandler<List<ClassModel>, ClassQuery.All, ListClassPort>() {
    override val useCase: ListClassPort by inject()
    override fun createQuery(page: Int, size: Int, id: String?): ClassQuery.All {
        return ClassQuery.All(page, size)
    }
}
