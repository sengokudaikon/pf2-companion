package io.sengokudaikon.dbfinder.domain.character.ancestry.entity

import io.sengokudaikon.dbfinder.persistence.character.ancestry.entity.AncestryPhysicalFeatures
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import io.sengokudaikon.dbfinder.domain.character.ancestry.model.AncestryPhysicalFeature as ModelAncestryPhysicalFeature

class AncestryPhysicalFeature(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<AncestryPhysicalFeature>(AncestryPhysicalFeatures)

    var ancestry by Ancestry referencedOn AncestryPhysicalFeatures.ancestryID
    var name by AncestryPhysicalFeatures.name
    var description by AncestryPhysicalFeatures.description
    var img by AncestryPhysicalFeatures.img
    var level by AncestryPhysicalFeatures.level

    fun toModel(): ModelAncestryPhysicalFeature {
        return ModelAncestryPhysicalFeature(
            this.name,
            this.description,
            this.img,
            this.level,
        )
    }
}
