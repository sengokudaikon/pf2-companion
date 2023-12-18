package io.sengokudaikon.isn.compendium.adapters.character.classs

import io.sengokudaikon.isn.compendium.domain.classs.ClassModel
import io.sengokudaikon.isn.compendium.operations.character.classs.query.ClassQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameClassPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class ClassNameHandler :
    ByNameHandler<ClassModel, ClassQuery.ByName, ByNameClassPort>() {
    override val useCase: ByNameClassPort by inject()
    override fun createQuery(name: String, id: String?): ClassQuery.ByName {
        return ClassQuery.ByName(name)
    }
}
