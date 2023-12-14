package io.sengokudaikon.isn.compendium.usecases.character.classs

import io.sengokudaikon.isn.compendium.domain.classs.ClassFeatureModel
import io.sengokudaikon.isn.compendium.domain.classs.repository.ClassFeatureRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.classs.query.ClassFeatureQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameClassFeatPort
import io.sengokudaikon.isn.infrastructure.usecases.GetByName

class ClassFeatByName(override val repository: ClassFeatureRepositoryPort) :
    GetByName<ClassFeatureQuery, ClassFeatureModel, ClassFeatureRepositoryPort>(), ByNameClassFeatPort {
    override fun getCacheKey(query: ClassFeatureQuery): String {
        query as ClassFeatureQuery.ByName
        return "model_classfeature:name:${query.name}"
    }
}
