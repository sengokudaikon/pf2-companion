package io.sengokudaikon.isn.compendium.adapters.character.classs

import io.sengokudaikon.isn.compendium.domain.classs.ClassModel
import io.sengokudaikon.isn.compendium.operations.character.classs.query.ClassQuery
import io.sengokudaikon.isn.compendium.ports.character.ByIdClassPort
import io.sengokudaikon.isn.infrastructure.adapters.ByIdHandler

class ClassIdHandler(override val useCase: ByIdClassPort) : ByIdHandler<ClassModel, ClassQuery.ById, ByIdClassPort>() {
    override fun createQuery(id: String): ClassQuery.ById {
        return ClassQuery.ById(id)
    }
}
