package io.sengokudaikon.isn.compendium.domain.character.ancestry.entity

import io.sengokudaikon.isn.compendium.persistence.character.ancestry.entity.Ancestries
import io.sengokudaikon.isn.compendium.persistence.character.ancestry.entity.AncestryBoosts
import io.sengokudaikon.isn.compendium.persistence.character.ancestry.entity.AncestryFeatures
import io.sengokudaikon.isn.compendium.persistence.character.ancestry.entity.AncestryFlaws
import io.sengokudaikon.isn.compendium.persistence.character.ancestry.entity.AncestryLanguages
import io.sengokudaikon.isn.compendium.persistence.character.ancestry.entity.AncestryTraits
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import io.sengokudaikon.isn.compendium.domain.character.ancestry.model.Ancestry as ModelAncestry

class Ancestry(
    id: EntityID<UUID>,
) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<Ancestry>(Ancestries)

    var name by Ancestries.name
    var description by Ancestries.description
    var rarity by Ancestries.rarity
    var hp by Ancestries.hitPoints
    var size by Ancestries.size
    var speed by Ancestries.speed
    var img by Ancestries.img
    var isArchived by Ancestries.isArchived
    var contentSrc by Ancestries.contentSrc
    var visionSense by VisionSense referencedOn Ancestries.visionSenseID
    var additionalSense by VisionSense optionalReferencedOn Ancestries.additionalSenseID
    val features by AncestryFeature referrersOn AncestryFeatures.ancestryID
    val abilityBoosts by AncestryBoost referrersOn AncestryBoosts.ancestryID
    val abilityFlaws by AncestryFlaw referrersOn AncestryFlaws.ancestryID
    val languages by AncestryLanguage referrersOn AncestryLanguages.ancestryID
    val traits by AncestryTrait referrersOn AncestryTraits.ancestryID
    var rules by Ancestries.rules

    suspend fun toModel(): ModelAncestry {
        return suspendedTransactionAsync {
            val model = ModelAncestry(
                id = this@Ancestry.id.value.toString(),
                name = this@Ancestry.name,
                description = this@Ancestry.description,
                rarity = this@Ancestry.rarity.name,
                hp = this@Ancestry.hp,
                size = this@Ancestry.size.name,
                speed = this@Ancestry.speed,
                artworkURL = this@Ancestry.img,
                isArchived = this@Ancestry.isArchived,
                contentSrc = this@Ancestry.contentSrc,
            )
            model.visionSense = this@Ancestry.visionSense.toModel()
            when {
                this@Ancestry.additionalSense != null -> {
                    model.additionalSense = this@Ancestry.additionalSense!!.toModel()
                }
            }
            when {
                !this@Ancestry.abilityBoosts.empty() -> {
                    model.abilityBoosts = this@Ancestry.abilityBoosts.map(AncestryBoost::toModel)
                }
            }
            when {
                !this@Ancestry.abilityFlaws.empty() -> {
                    model.abilityFlaws = this@Ancestry.abilityFlaws.map(AncestryFlaw::toModel)
                }
            }
            when {
                !this@Ancestry.languages.empty() -> {
                    model.languages = this@Ancestry.languages.map(AncestryLanguage::toModel)
                }
            }
            when {
                !this@Ancestry.features.empty() -> {
                    model.physicalFeatures = this@Ancestry.features.map(AncestryFeature::toModel)
                }
            }
            when {
                !this@Ancestry.traits.empty() -> {
                    model.traits = this@Ancestry.traits.map(AncestryTrait::toModel)
                }
            }
            model
        }.await()
    }
}
