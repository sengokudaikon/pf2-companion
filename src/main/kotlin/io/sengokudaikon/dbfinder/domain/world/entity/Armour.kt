package io.sengokudaikon.dbfinder.domain.world.entity

import io.sengokudaikon.dbfinder.persistence.inventory.entity.Armours
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Armour(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<Armour>(Armours)
    var name by Armours.name
    var itemId by Armours.itemId
    var proficiency by Armours.proficiency
    var acBonus by Armours.acBonus
    var dexCap by Armours.dexCap
    var strengthRequired by Armours.strengthRequired
    var checkPenalty by Armours.checkPenalty
    var speedPenalty by Armours.speedPenalty
    var armourType by Armours.armourType
}
