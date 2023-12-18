package io.sengokudaikon.isn.compendium.adapters.character.classs

import io.sengokudaikon.isn.compendium.domain.classs.ClassModel
import io.sengokudaikon.isn.compendium.operations.character.classs.query.ClassQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdClassPort
import io.sengokudaikon.isn.infrastructure.adapters.ByIdHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class ClassIdHandler : ByIdHandler<ClassModel, ClassQuery.ById, ByIdClassPort>() {
    override val useCase: ByIdClassPort by inject()
    override fun createQuery(id: String): ClassQuery.ById {
        return ClassQuery.ById(id)
    }
}
