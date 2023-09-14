package io.sengokudaikon.dbfinder.domain.character.companion.entity

import io.sengokudaikon.dbfinder.persistence.character.companion.entity.FamiliarAbilities
import io.sengokudaikon.dbfinder.persistence.character.companion.entity.Familiars
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Familiar(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<Familiar>(Familiars)

    var name by Familiars.name
    var description by Familiars.description
    var hp by Familiars.hp
    var rarity by Familiars.rarity
    var type by Familiars.type
    val abilities by FamiliarAbility referrersOn FamiliarAbilities.familiar
    var homebrewID by Familiars.homebrewID
}
