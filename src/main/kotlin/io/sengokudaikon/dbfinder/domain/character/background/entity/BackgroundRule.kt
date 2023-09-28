package io.sengokudaikon.dbfinder.domain.character.background.entity

import io.sengokudaikon.dbfinder.domain.world.entity.Rule
import io.sengokudaikon.dbfinder.persistence.character.background.entity.BackgroundRules
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import io.sengokudaikon.dbfinder.domain.world.model.Rule as ModelRule

class BackgroundRule(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    suspend fun toModel(): ModelRule {
        return rule.toModel()
    }

    companion object : KotlinxUUIDEntityClass<BackgroundRule>(BackgroundRules)

    var background by Background referencedOn BackgroundRules.background
    var rule by Rule referencedOn BackgroundRules.rule
}
