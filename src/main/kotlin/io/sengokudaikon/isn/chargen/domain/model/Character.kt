package io.sengokudaikon.isn.chargen.domain.model

import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryModel
import io.sengokudaikon.isn.compendium.domain.background.BackgroundModel
import io.sengokudaikon.isn.compendium.domain.classs.ClassModel
import io.sengokudaikon.isn.compendium.enums.BoundedInt
import io.sengokudaikon.isn.compendium.enums.DamageType
import io.sengokudaikon.isn.compendium.enums.Proficiency
import io.sengokudaikon.isn.compendium.enums.SaveType
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.codecs.kotlinx.ObjectIdSerializer
import org.bson.types.ObjectId

@Serializable
@OptIn(ExperimentalSerializationApi::class)
data class Character(
    @SerialName("_id")
    @Serializable(with = ObjectIdSerializer::class)
    val id: ObjectId,
    val name: String,
    val playerName: String,
    val level: Int,
    val currentExp: Int,
    val heroPoints: Int,
    val ancestry: AncestryModel,
    val heritage: String,
    val background: BackgroundModel,
    @SerialName("class")
    val classs: ClassModel,
    val attributes: List<AbilityScore>,
    val skills: List<Skill>,
    val saves: List<Save>,
    val hp: HP,
    val languages: List<String>,
    val perception: Perception,
    val speed: Speed,
    val strikes: List<Strike>,
    val weaponProficiencies: WeaponProficiency,
    val classDc: ClassDC,
    val skillFeats: List<SkillFeat>,
    val classFeats: List<ClassFeat>,
    val ancestryFeats: List<AncestryFeat>,
    val dedications: List<String>,
    val spellcasting: SpellCasting
) {
    @Serializable
    data class SpellCasting(
        val tradition: String,
        val spellcastingType: String,
        val spellDc: SpellDC,
        val spellAttack: SpellDC,
        val spellSlots: List<SpellSlot>,
        val spellRanks: List<Int>,
        val cantrips: List<String>,
        val focusSpells: List<String>,
        val innateSpells: List<String>,
        val spells: List<String>,
        val rituals: List<String>,
    )

    @Serializable
    data class SpellDC(
        val value: Int,
        val proficiency: Proficiency,
        val base: Int = 10,
        val fromAttribute: Int,
        val fromProficiency: Int,
        val fromItem: Int,
    )

    @Serializable
    data class SpellSlot(
        val level: Int,
        val max: Int,
        val prepared: Int,
        val used: Int
    )

    @Serializable
    data class SkillFeat(
        val level: Int,
        val name: String
    )

    @Serializable
    data class ClassFeat(
        val level: Int,
        val name: String
    )

    @Serializable
    data class AncestryFeat(
        val level: Int,
        val name: String
    )

    @Serializable
    data class ClassDC(
        val value: Int,
        val base: Int = 10,
        val fromAttribute: Int,
        val fromProficiency: Int,
        val fromItem: Int,
    )

    @Serializable
    data class WeaponProficiency(
        val unarmed: Proficiency,
        val simple: Proficiency,
        val martial: Proficiency,
        val advanced: Proficiency,
        val other: Proficiency,
        val additionalWeapons: List<String>,
        val criticalSpecializations: List<String>,
    )

    @Serializable
    data class Strike(
        val weapon: String,
        val value: Int,
        val fromAttribute: Int,
        val fromProficiency: Int,
        val fromItem: Int,
        val damage: String,
        val damageType: DamageType,
        val traits: List<String>
    )

    @Serializable
    data class Perception(
        val value: Int,
        val proficiency: Proficiency,
        val fromAttribute: Int,
        val fromProficiency: Int,
        val fromItem: Int,
        val senses: List<String>
    )

    @Serializable
    data class Speed(
        val value: Int,
        val fromItem: Int,
        val fromArmor: Int? = null,
        val specialMovement: List<String>
    )

    @Serializable
    data class AbilityScore(
        val name: String,
        val value: Int,
    )

    @Serializable
    data class Skill(
        val name: String,
        val modifier: Int,
        val proficiency: Proficiency,
        val fromAttribute: Int,
        val fromProficiency: Int,
        val fromItem: Int,
        val fromArmor: Int? = null,
    )

    @Serializable
    data class Save(
        val type: SaveType,
        val value: Int,
        val proficiency: Proficiency,
        val fromAttribute: Int,
        val fromProficiency: Int,
        val fromItem: Int,
    )

    @Serializable
    data class HP(
        val max: Int,
        val current: Int,
        val temporary: Int,
        val dying: BoundedInt = BoundedInt(0, 4),
        val wounded: BoundedInt = BoundedInt(0, 4)
    )
}
