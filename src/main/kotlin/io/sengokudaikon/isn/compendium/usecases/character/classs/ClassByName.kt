package io.sengokudaikon.isn.compendium.usecases.character.classs

import io.sengokudaikon.isn.compendium.domain.classs.ClassModel
import io.sengokudaikon.isn.compendium.domain.classs.repository.ClassRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.classs.query.ClassQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameClassPort
import io.sengokudaikon.isn.infrastructure.usecases.GetByName

class ClassByName(override val repository: ClassRepositoryPort) :
    GetByName<ClassQuery, ClassModel, ClassRepositoryPort>(), ByNameClassPort {
    override fun getCacheKey(query: ClassQuery): String {
        query as ClassQuery.ByName
        return "model_class:name:${query.name}"
    }
}
