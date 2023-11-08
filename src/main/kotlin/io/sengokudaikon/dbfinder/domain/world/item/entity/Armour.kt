package io.sengokudaikon.dbfinder.domain.world.item.entity

import io.sengokudaikon.dbfinder.persistence.items.entity.Armours
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import io.sengokudaikon.dbfinder.domain.world.item.model.Armour as ModelArmour

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

    fun toModel(): ModelArmour {
        return ModelArmour(
            name = this.name,
            itemId = this.itemId.toString(),
            proficiency = this.proficiency,
            acBonus = this.acBonus,
            dexCap = this.dexCap,
            strengthRequired = this.strengthRequired,
            checkPenalty = this.checkPenalty,
            speedPenalty = this.speedPenalty,
            armourType = this.armourType,
        )
    }
}
