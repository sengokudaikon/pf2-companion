package io.sengokudaikon.isn.compendium.usecases.character.classs

import io.sengokudaikon.isn.compendium.domain.classs.ClassFeatureModel
import io.sengokudaikon.isn.compendium.domain.classs.repository.ClassFeatureRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.classs.query.ClassFeatureQuery
import io.sengokudaikon.isn.compendium.ports.character.ListClassFeatPort
import io.sengokudaikon.isn.infrastructure.usecases.GetList

class ClassFeatList(override val repository: ClassFeatureRepositoryPort) :
    GetList<ClassFeatureQuery, ClassFeatureModel, List<ClassFeatureModel>, ClassFeatureRepositoryPort>(),
    ListClassFeatPort {
    override fun getCacheKey(query: ClassFeatureQuery): String {
        query as ClassFeatureQuery.All
        return "model_classfeature:all:${query.page}:${query.size}"
    }
}
