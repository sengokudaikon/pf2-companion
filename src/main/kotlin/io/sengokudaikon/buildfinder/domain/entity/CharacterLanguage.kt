package io.sengokudaikon.buildfinder.domain.entity

import io.sengokudaikon.buildfinder.persistence.entity.CharacterLanguages
import io.sengokudaikon.dbfinder.domain.world.entity.Language
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CharacterLanguage(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<CharacterLanguage>(CharacterLanguages)

    var character by Character referencedOn CharacterLanguages.characterID
    var language by Language referencedOn CharacterLanguages.languageID
}
