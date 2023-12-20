package io.sengokudaikon.isn.compendium.adapters.character.classs

import io.sengokudaikon.isn.compendium.domain.classs.ClassFeatureModel
import io.sengokudaikon.isn.compendium.operations.character.classs.query.ClassFeatureQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdClassFeatPort
import io.sengokudaikon.isn.infrastructure.adapters.ByIdHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class ClassFeatIdHandler :
    ByIdHandler<ClassFeatureModel, ClassFeatureQuery.ById, ByIdClassFeatPort>() {
    override val useCase: ByIdClassFeatPort by inject()
    override fun createQuery(id: String, secondaryId: String?): ClassFeatureQuery.ById {
        return ClassFeatureQuery.ById(id)
    }
}
