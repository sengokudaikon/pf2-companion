package io.sengokudaikon.isn.chargen.domain.entity

import io.sengokudaikon.isn.chargen.persistence.entity.CharacterAttributes
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CharacterAttribute(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object :
        KotlinxUUIDEntityClass<CharacterAttribute>(CharacterAttributes)

    var character by Character referencedOn CharacterAttributes.characterID
    var attribute by CharacterAttributes.attribute
    var value by CharacterAttributes.value
}
