package io.sengokudaikon.dbfinder.domain.character.background.entity

import io.sengokudaikon.dbfinder.persistence.character.background.entity.Backgrounds
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Background(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<Background>(Backgrounds)

    var name by Backgrounds.name
    var rarity by Backgrounds.rarity
    var description by Backgrounds.description
    var code by Backgrounds.code
    var isArchived by Backgrounds.isArchived
    var contentSrc by Backgrounds.contentSrc
    var homebrewID by Backgrounds.homebrewID
    var version by Backgrounds.version
}
