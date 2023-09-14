package io.sengokudaikon.buildfinder.domain.entity

import io.sengokudaikon.buildfinder.persistence.entity.CharacterFamiliars
import io.sengokudaikon.dbfinder.domain.character.companion.entity.Familiar
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CharacterFamiliar(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<CharacterFamiliar>(CharacterFamiliars)

    val character by Character optionalReferencedOn CharacterFamiliars.characterID
    val familiar by Familiar referencedOn CharacterFamiliars.familiarID
}
