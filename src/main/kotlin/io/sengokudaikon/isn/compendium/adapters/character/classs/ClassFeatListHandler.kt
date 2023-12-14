package io.sengokudaikon.isn.compendium.adapters.character.classs

import io.sengokudaikon.isn.compendium.domain.classs.ClassFeatureModel
import io.sengokudaikon.isn.compendium.operations.character.classs.query.ClassFeatureQuery
import io.sengokudaikon.isn.compendium.ports.character.ListClassFeatPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler

class ClassFeatListHandler(override val useCase: ListClassFeatPort) : ListHandler<List<ClassFeatureModel>, ClassFeatureQuery.All, ListClassFeatPort>() {
    override fun createQuery(page: Int, size: Int, id: String?): ClassFeatureQuery.All {
        return ClassFeatureQuery.All(id!!, page, size)
    }
}
