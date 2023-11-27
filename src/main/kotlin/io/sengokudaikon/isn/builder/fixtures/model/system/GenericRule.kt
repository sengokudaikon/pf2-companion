package io.sengokudaikon.isn.builder.fixtures.model.system

import io.sengokudaikon.isn.compendium.enums.RuleMode
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenericRule(
    val domain: String? = null,
    val key: String,
    val label: String,
    val type: String? = null,
    val option: String? = null,
    val suboptions: List<SubOption>? = emptyList(),
    val category: String? = null,
    val toggleable: Boolean? = null,
    val damageDice: String? = null,
    val damageType: String? = null,
    val overrides: List<String>? = emptyList(),
    val adjustment: Map<String, String>? = emptyMap(),
    val effects: List<RuleEffect>? = emptyList(),
    val range: String? = null,
    val predicate: List<Predicate> = emptyList(),
    val hasHands: Boolean? = null,
    val selector: String? = null,
    val slug: String? = null,
    val value: Value? = null,
) {
    val mode: String
        get() {
            return when {
                key.contains("Modifier") -> RuleMode.MODIFIER.name
                key.contains("Resistances") || key.contains("Weakness") -> RuleMode.OVERRIDE.name
                key.contains("Selection") || key.contains("MultipleChoice") -> RuleMode.SELECTOR.name
                else -> RuleMode.ADDITION.name
            }
        }

    @Serializable
    data class Overrides(
        val armorClass: Map<String, String>?,
        val resistances: List<Resistance>?,
        val senses: Map<String, String>?,
        val size: String?,
        val skills: Map<String, Map<String, Int>>?,
        val speeds: Map<String, Int>?,
        val strikes: Map<String, Strikes>?,
        val tempHP: Int?,
        val weaknesses: List<Weakness>?,
    )

    @Serializable
    data class Resistance(
        val type: String,
        val value: Int,
    )

    @Serializable
    data class Weakness(
        val type: String,
        val value: Int,
    )

    @Serializable
    data class Strikes(
        val ability: String,
        val baseType: String,
        val category: String,
        val damage: Damage,
        val modifier: Int,
        val traits: List<String>,
    )

    @Serializable
    data class Damage(
        val damageType: String,
        val dice: Int,
        val die: String,
        val modifier: Int?,
    )

    @Serializable
    sealed class Predicate {
        @Serializable
        data class SimplePredicate(val value: String) : Predicate() {
            override fun toString(): String {
                return value
            }
        }

        @Serializable
        @SerialName("or")
        data class OrPredicate(val or: List<Predicate>) : Predicate() {
            override fun toString(): String {
                return or.joinToString(" || ") { it.toString() }
            }
        }

        @Serializable
        @SerialName("and")
        data class AndPredicate(val and: List<Predicate>) : Predicate() {
            override fun toString(): String {
                return and.joinToString(" && ") { it.toString() }
            }
        }
    }

    @Serializable
    data class SubOption(
        val label: String,
        val value: String,
    )

    @Serializable
    data class RuleEffect(
        val affects: String,
        val events: List<String>,
        val includesSelf: Boolean,
        val predicate: List<Predicate>,
        val uuid: String,
    )
}
