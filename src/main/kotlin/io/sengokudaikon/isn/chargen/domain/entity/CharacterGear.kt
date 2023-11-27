package io.sengokudaikon.isn.chargen.domain.entity

import io.sengokudaikon.isn.compendium.domain.world.item.entity.Item
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CharacterGear(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object :
        KotlinxUUIDEntityClass<CharacterGear>(io.sengokudaikon.isn.chargen.persistence.entity.CharacterGears)

    var character by Character referencedOn io.sengokudaikon.isn.chargen.persistence.entity.CharacterGears.characterID
    var gear by Item referencedOn io.sengokudaikon.isn.chargen.persistence.entity.CharacterGears.gearID
    var isInvested by io.sengokudaikon.isn.chargen.persistence.entity.CharacterGears.isInvested
    var quantity by io.sengokudaikon.isn.chargen.persistence.entity.CharacterGears.quantity
}
