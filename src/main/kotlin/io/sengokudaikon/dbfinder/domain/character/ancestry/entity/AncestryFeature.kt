package io.sengokudaikon.dbfinder.domain.character.ancestry.entity

import io.sengokudaikon.dbfinder.persistence.character.ancestry.entity.AncestryFeatures
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import io.sengokudaikon.dbfinder.domain.character.ancestry.model.AncestryFeature as ModelAncestryPhysicalFeature

class AncestryFeature(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<AncestryFeature>(AncestryFeatures)

    var ancestry by Ancestry referencedOn AncestryFeatures.ancestryID
    var name by AncestryFeatures.name
    var description by AncestryFeatures.description
    var img by AncestryFeatures.image
    var level by AncestryFeatures.level

    fun toModel(): ModelAncestryPhysicalFeature {
        return ModelAncestryPhysicalFeature(
            this.name,
            this.description,
            this.img,
            this.level,
        )
    }
}
