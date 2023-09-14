package io.sengokudaikon.dbfinder.fixtures

import io.sengokudaikon.dbfinder.domain.character.ancestry.model.AdditionalLanguages
import io.sengokudaikon.dbfinder.domain.character.ancestry.model.AncestryDTO
import io.sengokudaikon.dbfinder.domain.character.ancestry.model.AncestryPhysicalFeature
import io.sengokudaikon.dbfinder.domain.character.ancestry.model.VisionSense
import io.sengokudaikon.dbfinder.domain.world.model.Language
import io.sengokudaikon.dbfinder.domain.world.model.Trait
import io.sengokudaikon.dbfinder.operations.character.dto.Ability
import io.sengokudaikon.dbfinder.persistence.enums.Rarity
import io.sengokudaikon.dbfinder.persistence.enums.Size
import io.sengokudaikon.dbfinder.persistence.enums.VisionType
import kotlinx.serialization.Serializable

@Serializable
data class Anadi(
    override val img: String = "https://2e.aonprd.com/Images/Ancestries/Anadi.png",
    override val name: String = "Anadi",
    override val rarity: Rarity = Rarity.RARE,
    override val additionalLanguages: AdditionalLanguages = AdditionalLanguages(
        values = listOf(
            Language("Draconic"),
            Language("Elven"),
            Language("Sylvan"),
            Language("Gnoll"),
            Language("Iruxi"),
            Language("Orcish"),
        ),
    ),
    override val boosts: List<Ability> = listOf(
        Ability.Dexterity,
        Ability.Wisdom,
        Ability.Anything,
    ),
    override val description: String = "<p><em>Anadi people are reclusive, sapient spiders who hail from the jungles of southern Garund. Though they act in many ways like natural-born shapeshifters, their twin forms actually stem from carefully developed magic.</em></p>\n<hr />\n<p>As a communal and peaceful people, anadi ancestors endeavored to establish trade with the neighbors of ttheir homeland. However, these anadi soon learned that most others found their appearance to be extremely objectionable. Wishing to avoid conflict, ancient anadi retreated into isolation until they could find a solution. The answer came when their greatest scholars innovated a fusion of transmutation and illusion magic that allowed them to assume a humanoid form. The technique was developed, perfected, and eventually taught to the overwhelming majority of anadi.</p>\n<p>Early efforts with their new approach to diplomacy have yielded much better results, though sporadic contact means that some outsiders whisper false legends about anadi, such as claims that they are humans who transform into monstrous spiders at moonrise. Even contemporary explorers have reported anadi as human-spider hybrids. The anadi people of the current day strive to slowly but surely create a world where they no longer need to hide their true nature.</p>",
    override val flaws: List<Ability> = listOf(
        Ability.Constitution,
    ),
    override val hp: Int = 8,
    override val languages: List<Language> = listOf(
        Language("Anadi"),
        Language("Mwangi"),
    ),
    override val reach: Int = 5,
    override val rules: List<AncestryFixtureLoader.JsonRules> = listOf(),
    override val size: Size = Size.MEDIUM,
    override val source: String = "Pathfinder Lost Omens: The Mwangi Expanse",
    override val speed: Int = 25,
    override val traits: List<Trait> = listOf(
        Trait("Anadi", "A reclusive people from Garund who resemble spiders and can assume human forms.", true, "https://2e.aonprd.com/Traits.aspx?ID=255"),
        Trait("Humanoid", "Humanoid creatures reason and act much like humans. They typically stand upright and have two arms and two legs.", true, "https://2e.aonprd.com/Traits.aspx?ID=91"),
    ),
    override val vision: VisionSense = VisionSense(
        VisionType.NORMAL,
        60000000,
        true,
    ),
    override val additionalSense: VisionSense? = null,
    override val physicalFeatures: List<AncestryPhysicalFeature> = listOf(
        AncestryPhysicalFeature(
            "Change Shape (Anadi)",
            "You change into your human or spider shape. Each shape has a specific, persistent appearance. In your human shape, you can't use unarmed attacks granted by your ancestry. You aren't flat-footed when climbing in your spider shape. However, in your spider shape you can't use weapons, shields, or other held items of any sort, and you are limited in what actions you can take that have the manipulate trait. The only manipulate actions you can take are to Cast a Spell with somatic components, weave silk or webbing, or simple Interact actions such as opening an unlocked door. Your spider legs can't perform actions that require fingers or significant manual dexterity, including any action that would require a check to accomplish. The GM might determine other manipulate actions are appropriate for your spider legs.",
            "systems/pf2e/icons/default-icons/feat.svg",
            1,
            traits = listOf(
                Trait("Anadi", "A reclusive people from Garund who resemble spiders and can assume human forms.", true, "https://2e.aonprd.com/Traits.aspx?ID=255"),
                Trait("Arcane", "This magic comes from the arcane tradition, which is built on logic and rationality. Anything with this trait is magical.", true, "https://2e.aonprd.com/Traits.aspx?ID=11"),
                Trait("Concentrate", "An action with this trait requires a degree of mental concentration and discipline.", true, "https://2e.aonprd.com/Traits.aspx?ID=32"),
                Trait("Polymorph", "These effects transform the target into a new form. A target can't be under the effect of more than one polymorph effect at a time. If it comes under the effect of a second polymorph effect, the second polymorph effect attempts to counteract the first. If it succeeds, it takes effect, and if it fails, the spell has no effect on that target. Any Strikes specifically granted by a polymorph effect are magical. Unless otherwise stated, polymorph spells don't allow the target to take on the appearance of a specific individual creature, but rather just a generic creature of a general type or ancestry.\n" + "If you take on a battle form with a polymorph spell, the special statistics can be adjusted only by circumstance bonuses, status bonuses, and penalties. Unless otherwise noted, the battle form prevents you from casting spells, speaking, and using most manipulate actions that require hands. (If there's doubt about whether you can use an action, the GM decides.) Your gear is absorbed into you; the constant abilities of your gear still function, but you can't activate any items.", true, "https://2e.aonprd.com/Traits.aspx?ID=127"),
                Trait("Transmutation", "Effects and magic items with this trait are associated with the transmutation school of magic, typically changing something's form.", true, "https://2e.aonprd.com/Traits.aspx?ID=157"),
            ),
        ),
        AncestryPhysicalFeature(
            "Fangs",
            "You were born with a natural means for hunting and self-defense. You gain a fangs unarmed attack that deals 1d6 piercing damage. Your fangs are in the brawling group and have the finesse and unarmed traits.",
            "systems/pf2e/icons/default-icons/feat.svg",
            1,
        ),
    ),
) : AncestryDTO
