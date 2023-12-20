package io.sengokudaikon.isn.chargen.domain.model

import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryFeatureModel
import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryModel
import io.sengokudaikon.isn.compendium.domain.background.BackgroundModel
import io.sengokudaikon.isn.compendium.domain.classs.ClassFeatureModel
import io.sengokudaikon.isn.compendium.domain.classs.ClassModel
import io.sengokudaikon.isn.compendium.domain.equipment.model.WeaponModel
import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.domain.heritage.HeritageModel
import io.sengokudaikon.isn.compendium.domain.spell.SpellModel
import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.domain.system.SystemModel
import io.sengokudaikon.isn.compendium.domain.system.Traits
import io.sengokudaikon.isn.compendium.enums.BoundedInt
import io.sengokudaikon.isn.compendium.enums.DamageType
import io.sengokudaikon.isn.compendium.enums.Proficiency
import io.sengokudaikon.isn.compendium.enums.SaveType
import io.sengokudaikon.isn.infrastructure.domain.Model
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.BsonValue
import org.bson.codecs.kotlinx.BsonValueSerializer
import org.bson.codecs.kotlinx.ObjectIdSerializer
import org.bson.types.ObjectId

@Serializable
@OptIn(ExperimentalSerializationApi::class)
data class CharacterModel(
    @SerialName("_id")
    @Serializable(with = ObjectIdSerializer::class)
    override val id: ObjectId,
    override val name: String,
    override val img: String,
    override val type: String = "character",
    val playerName: String,
    override val system: CharacterSystem,
) : Model {
    @Serializable
    data class CharacterSystem(
        override val description: DescriptionType,
        override val traits: Traits? = null,
        override val publication: Publication = Publication("ORC", true, "Inner Sea Navigator"),
        @OptIn(ExperimentalSerializationApi::class)
        @Serializable(with = BsonValueSerializer::class) override val rules: BsonValue? = null,
        val level: Int,
        val currentExp: Int,
        val heroPoints: Int,
        val ancestry: AncestryModel,
        val heritage: HeritageModel,
        val background: BackgroundModel,
        @SerialName("class")
        val baseClass: ClassModel,
        val dualClass: ClassModel? = null,
        val attributes: List<AbilityScore>,
        val attributesLeveledAt: Map<Int, List<AbilityScore>>,
        val skills: List<Skill>,
        val saves: List<Save>,
        val hp: HP,
        val languages: List<String>,
        val perception: Perception,
        val speed: Speed,
        val strikes: List<Strike>,
        val weaponProficiencies: WeaponProficiency,
        val classDc: ClassDC,
        val generalFeats: List<FeatModel>,
        val skillFeats: List<FeatModel>,
        val classFeats: List<ClassFeatureModel>,
        val ancestryFeats: List<AncestryFeatureModel>,
        val dedications: List<DedicationModel>,
        val dedicationFeats: List<FeatModel>,
        val spellcasting: List<SpellCasting>,
        val formula: List<String>,
        val pets: List<AnimalCompanionModel>,
        val familiars: List<FamiliarModel>,
    ) : SystemModel

    @Serializable
    data class SpellCasting(
        val origin: String,
        val tradition: String,
        val spellcastingType: String,
        val spellDc: SpellDC,
        val spellAttack: SpellDC,
        val spellSlots: List<SpellSlot>,
        val spellRanks: List<Int>,
        val cantrips: List<SpellModel>,
        val focusSpells: List<SpellModel>,
        val innateSpells: List<SpellModel>,
        val spells: List<SpellModel>,
        val rituals: List<SpellModel>,
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
        var value: Int,
        val base: Int = 10,
        val fromLevel: Int = 1,
        val fromAttribute: Int,
        val fromProficiency: Int,
        val fromItem: Int,
    ) {
        fun calculate(): Int {
            value = base + fromAttribute + fromProficiency + fromItem + fromLevel
            return value
        }
    }

    @Serializable
    data class WeaponProficiency(
        val unarmed: Int,
        val simple: Int,
        val martial: Int,
        val advanced: Int,
        val other: Proficiency,
        val additionalWeapons: List<String>,
        val criticalSpecializations: List<String>,
    )

    @Serializable
    data class Strike(
        val weapon: String,
        val value: Int,
        val fromAttribute: Int,
        var fromProficiency: Int,
        val fromItem: Int,
        val damage: String,
        val damageType: DamageType,
        val traits: List<String>,
        val additionalDamage: List<String>,
    ) {
        lateinit var itemWeapon: WeaponModel
    }

    @Serializable
    data class Perception(
        var value: Int = 0,
        val proficiency: Proficiency,
        val fromAttribute: Int,
        val fromProficiency: Int,
        val fromItem: Int,
        val fromLevel: Int = 1,
        val senses: List<String>
    ) {
        fun calculate(): Int {
            value = fromAttribute + fromProficiency + fromItem + fromLevel
            return value
        }
    }

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
        var modifier: Int = 0,
        val proficiency: Proficiency,
        val fromAttribute: Int,
        val fromProficiency: Int,
        val fromItem: Int,
        val fromLevel: Int,
        val fromArmor: Int? = null,
    ) {
        fun calculate(): Int {
            modifier = fromAttribute + fromProficiency + fromItem + fromLevel
            return modifier
        }
    }

    @Serializable
    data class Save(
        val type: SaveType,
        var value: Int = 0,
        val proficiency: Proficiency,
        val fromAttribute: Int,
        val fromProficiency: Int,
        val fromItem: Int,
        val fromLevel: Int
    ) {
        fun calculate(): Int {
            value = fromAttribute + fromProficiency + fromItem + fromLevel
            return value
        }
    }

    @Serializable
    data class HP(
        val max: Int,
        val current: Int,
        val temporary: Int,
        val dying: BoundedInt = BoundedInt(0),
        val wounded: BoundedInt = BoundedInt(0)
    )
}
