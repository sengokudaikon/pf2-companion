package io.sengokudaikon.isn.compendium.domain.character.ancestry.entity

import io.sengokudaikon.isn.compendium.persistence.character.ancestry.entity.VisionSenses
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class VisionSense(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<VisionSense>(VisionSenses)

    var name by VisionSenses.name
    var visionRange by VisionSenses.visionRange
    var precision by VisionSenses.precision

    fun toModel(): io.sengokudaikon.isn.compendium.domain.character.ancestry.model.VisionSense {
        return io.sengokudaikon.isn.compendium.domain.character.ancestry.model.VisionSense(
            this.name,
            this.visionRange,
            this.precision.name == VisionSenses.Precision.PRECISE.name,
        )
    }
}
