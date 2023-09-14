package io.sengokudaikon.dbfinder.domain.character.ancestry.entity

import io.sengokudaikon.dbfinder.domain.world.entity.Rule
import io.sengokudaikon.dbfinder.persistence.character.ancestry.entity.AncestryRules
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import io.sengokudaikon.dbfinder.domain.character.ancestry.model.AncestryRule as ModelAncestryRule

class AncestryRule(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<AncestryRule>(AncestryRules)

    var ancestryID by Ancestry referencedOn AncestryRules.ancestryID
    var ruleID by Rule referencedOn AncestryRules.ruleID
    fun toModel(): ModelAncestryRule {
        return ModelAncestryRule(
            ruleID.name,
            ruleID.description,
        )
    }
}
