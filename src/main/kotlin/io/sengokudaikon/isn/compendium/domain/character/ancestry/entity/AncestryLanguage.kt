package io.sengokudaikon.isn.compendium.domain.character.ancestry.entity

import io.sengokudaikon.isn.compendium.domain.world.global.entity.Language
import io.sengokudaikon.isn.compendium.persistence.character.ancestry.entity.AncestryLanguages
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import io.sengokudaikon.isn.compendium.domain.world.global.model.Language as ModelLanguage

class AncestryLanguage(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<AncestryLanguage>(AncestryLanguages)

    var ancestryID by Ancestry referencedOn AncestryLanguages.ancestryID
    var language by Language referencedOn AncestryLanguages.languageID
    var isBonus by AncestryLanguages.isBonus

    fun toModel(): ModelLanguage {
        return ModelLanguage(
            this.language.name,
            this.language.description,
        )
    }
}
