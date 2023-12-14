package io.sengokudaikon.isn.compendium.adapters.character.classs

import io.sengokudaikon.isn.compendium.domain.classs.ClassModel
import io.sengokudaikon.isn.compendium.operations.character.classs.query.ClassQuery
import io.sengokudaikon.isn.compendium.ports.character.ListClassPort
import io.sengokudaikon.isn.infrastructure.adapters.ListHandler

class ClassListHandler(override val useCase: ListClassPort) :
    ListHandler<List<ClassModel>, ClassQuery.All, ListClassPort>() {
    override fun createQuery(page: Int, size: Int, id: String?): ClassQuery.All {
        return ClassQuery.All(page, size)
    }
}
