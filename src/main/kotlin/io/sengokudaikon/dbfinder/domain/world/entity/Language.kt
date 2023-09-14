package io.sengokudaikon.dbfinder.domain.world.entity

import io.sengokudaikon.dbfinder.persistence.world.entity.Languages
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import io.sengokudaikon.dbfinder.domain.world.model.Language as ModelLanguage

class Language(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<Language>(Languages)
    var name by Languages.name
    var description by Languages.description
    var homebrewID by Languages.homebrewID

    fun toModel(): ModelLanguage {
        return ModelLanguage(
            this.name,
            this.description,
        )
    }
}
