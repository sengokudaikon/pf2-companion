package io.sengokudaikon.isn.compendium.adapters.character.classs

import io.sengokudaikon.isn.compendium.domain.classs.ClassFeatureModel
import io.sengokudaikon.isn.compendium.operations.character.classs.query.ClassFeatureQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameClassFeatPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler
import io.sengokudaikon.isn.infrastructure.adapters.QueryHandler
import org.koin.core.annotation.Single
import org.koin.core.component.inject

@Single(binds = [QueryHandler::class])
class ClassFeatNameHandler : ByNameHandler<ClassFeatureModel, ClassFeatureQuery.ByName, ByNameClassFeatPort>() {
    override val useCase: ByNameClassFeatPort by inject()
    override fun createQuery(name: String, id: String?): ClassFeatureQuery.ByName {
        return ClassFeatureQuery.ByName(name)
    }
}
