package io.sengokudaikon.isn.compendium.domain.world.global.entity

import io.sengokudaikon.isn.compendium.persistence.world.entity.Languages
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import io.sengokudaikon.isn.compendium.domain.world.global.model.Language as ModelLanguage

class Language(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<Language>(Languages)

    var name by Languages.name
    var description by Languages.description

    fun toModel(): ModelLanguage {
        return ModelLanguage(
            this.name,
            this.description,
        )
    }
}
