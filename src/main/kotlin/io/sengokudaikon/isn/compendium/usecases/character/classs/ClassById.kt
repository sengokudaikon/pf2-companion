package io.sengokudaikon.isn.compendium.usecases.character.classs

import io.sengokudaikon.isn.compendium.domain.classs.ClassModel
import io.sengokudaikon.isn.compendium.domain.classs.repository.ClassRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.classs.query.ClassQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdClassPort
import io.sengokudaikon.isn.infrastructure.usecases.GetById
import org.koin.core.annotation.Single

@Single(binds = [ByIdClassPort::class])
class ClassById(override val repository: ClassRepositoryPort) :
    GetById<ClassQuery, ClassModel>(),
    ByIdClassPort {
    override fun getCacheKey(query: ClassQuery): String {
        query as ClassQuery.ById
        return "model_class:id:${query.id}"
    }
}
