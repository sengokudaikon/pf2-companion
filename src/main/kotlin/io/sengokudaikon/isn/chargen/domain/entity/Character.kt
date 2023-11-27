package io.sengokudaikon.isn.chargen.domain.entity

import io.sengokudaikon.isn.app.domain.user.entity.User
import io.sengokudaikon.isn.chargen.persistence.entity.CharacterAnimalCompanions
import io.sengokudaikon.isn.chargen.persistence.entity.CharacterArmours
import io.sengokudaikon.isn.chargen.persistence.entity.CharacterAttributes
import io.sengokudaikon.isn.chargen.persistence.entity.CharacterFamiliars
import io.sengokudaikon.isn.chargen.persistence.entity.CharacterFeats
import io.sengokudaikon.isn.chargen.persistence.entity.CharacterKnownSpells
import io.sengokudaikon.isn.chargen.persistence.entity.CharacterLanguages
import io.sengokudaikon.isn.chargen.persistence.entity.CharacterPreparedSpells
import io.sengokudaikon.isn.chargen.persistence.entity.CharacterSkills
import io.sengokudaikon.isn.chargen.persistence.entity.CharacterSpellSlots
import io.sengokudaikon.isn.chargen.persistence.entity.CharacterSpellcastings
import io.sengokudaikon.isn.chargen.persistence.entity.CharacterWeapons
import io.sengokudaikon.isn.chargen.persistence.entity.Characters
import io.sengokudaikon.isn.chargen.persistence.entity.Inventory
import io.sengokudaikon.isn.compendium.domain.character.ancestry.entity.Ancestry
import io.sengokudaikon.isn.compendium.domain.character.background.entity.Background
import io.sengokudaikon.isn.compendium.domain.character.classs.entity.Class
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Character(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<Character>(Characters)

    var name by Characters.name
    var buildId by Characters.buildId
    val user by User referencedOn Characters.userID
    val ancestry by Ancestry referencedOn Characters.ancestryID
    val background by Background referencedOn Characters.backgroundID
    val heritage by Characters.heritageID
    val charClass by Class referencedOn Characters.classID
    val charClass2 by Class optionalReferencedOn Characters.classID2
    val level by Characters.level
    val health by Characters.health
    val currentHealth by Characters.currentHealth
    val tempHealth by Characters.tempHealth
    val heroPoints by Characters.heroPoints
    val experience by Characters.experience
    val attributes by CharacterAttribute referrersOn CharacterAttributes.characterID
    val skills by CharacterSkill referrersOn CharacterSkills.characterID
    val feats by CharacterFeat referrersOn CharacterFeats.characterID
    val languages by CharacterLanguage referrersOn CharacterLanguages.characterID
    val inventory by CharacterInventory referencedOn Inventory.characterID
    val weapons by CharacterWeapon referrersOn CharacterWeapons.characterID
    val armour by CharacterArmour referrersOn CharacterArmours.characterID
    val spellCasting by CharacterSpellcasting referrersOn CharacterSpellcastings.characterID
    val knownSpells by CharacterKnownSpell referrersOn CharacterKnownSpells.characterID
    val preparedSpells by CharacterPreparedSpell referrersOn CharacterPreparedSpells.characterID
    val spellSlots by CharacterSpellSlot referrersOn CharacterSpellSlots.characterID
    val animalCompanions by CharacterAnimalCompanion optionalReferrersOn CharacterAnimalCompanions.characterID
    val familiars by CharacterFamiliar optionalReferrersOn CharacterFamiliars.characterID
}
