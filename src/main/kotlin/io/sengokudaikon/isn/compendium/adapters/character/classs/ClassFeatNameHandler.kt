package io.sengokudaikon.isn.compendium.adapters.character.classs

import io.sengokudaikon.isn.compendium.domain.classs.ClassFeatureModel
import io.sengokudaikon.isn.compendium.operations.character.classs.query.ClassFeatureQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameClassFeatPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler

class ClassFeatNameHandler(override val useCase: ByNameClassFeatPort) : ByNameHandler<ClassFeatureModel, ClassFeatureQuery.ByName, ByNameClassFeatPort>() {
    override fun createQuery(name: String, id: String?): ClassFeatureQuery.ByName {
        return ClassFeatureQuery.ByName(id!!, name)
    }
}
