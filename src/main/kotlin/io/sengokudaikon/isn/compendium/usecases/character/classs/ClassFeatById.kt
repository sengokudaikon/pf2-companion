package io.sengokudaikon.isn.compendium.usecases.character.classs

import io.sengokudaikon.isn.compendium.domain.classs.ClassFeatureModel
import io.sengokudaikon.isn.compendium.domain.classs.repository.ClassFeatureRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.classs.query.ClassFeatureQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdClassFeatPort
import io.sengokudaikon.isn.infrastructure.usecases.GetById
import org.koin.core.annotation.Single

@Single(binds = [ByIdClassFeatPort::class])
class ClassFeatById(override val repository: ClassFeatureRepositoryPort) :
    GetById<ClassFeatureQuery, ClassFeatureModel, ClassFeatureRepositoryPort>(), ByIdClassFeatPort {
    override fun getCacheKey(query: ClassFeatureQuery): String {
        query as ClassFeatureQuery.ById
        return "model_classfeature:id:${query.id}"
    }
}
