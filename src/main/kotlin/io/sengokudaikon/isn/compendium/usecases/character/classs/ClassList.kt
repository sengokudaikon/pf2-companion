package io.sengokudaikon.isn.compendium.usecases.character.classs

import io.sengokudaikon.isn.compendium.domain.classs.ClassModel
import io.sengokudaikon.isn.compendium.domain.classs.repository.ClassRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.classs.query.ClassQuery
import io.sengokudaikon.isn.compendium.ports.character.ListClassPort
import io.sengokudaikon.isn.infrastructure.usecases.GetList
import org.koin.core.annotation.Single

@Single(binds = [ListClassPort::class])
class ClassList(override val repository: ClassRepositoryPort) :
    GetList<ClassQuery, ClassModel, List<ClassModel>, ClassRepositoryPort>(), ListClassPort {
    override fun getCacheKey(query: ClassQuery): String {
        query as ClassQuery.All
        return "model_class:all:${query.page}:${query.size}"
    }
}
