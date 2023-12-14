package io.sengokudaikon.isn.compendium.adapters.character.classs

import io.sengokudaikon.isn.compendium.domain.classs.ClassModel
import io.sengokudaikon.isn.compendium.operations.character.classs.query.ClassQuery
import io.sengokudaikon.isn.compendium.ports.character.ByNameClassPort
import io.sengokudaikon.isn.infrastructure.adapters.ByNameHandler

class ClassNameHandler(override val useCase: ByNameClassPort) :
    ByNameHandler<ClassModel, ClassQuery.ByName, ByNameClassPort>() {
    override fun createQuery(name: String, id: String?): ClassQuery.ByName {
        return ClassQuery.ByName(name)
    }
}
