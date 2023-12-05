package io.sengokudaikon.isn.compendium.domain.system

import io.sengokudaikon.isn.compendium.operations.global.dto.DamageType
import io.sengokudaikon.isn.compendium.operations.global.response.RuleResponse
import io.sengokudaikon.isn.compendium.operations.global.vo.Choice
import io.sengokudaikon.isn.compendium.operations.global.vo.SubOption
import io.sengokudaikon.isn.infrastructure.operations.transform
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import org.bson.BsonValue
import org.bson.codecs.kotlinx.BsonValueSerializer

@Serializable
@OptIn(ExperimentalSerializationApi::class)
data class GenericRule(
    val domain: String? = null,
    val key: String? = null,
    val label: String? = null,
    val mode: String? = null,
    val type: String? = null,
    val option: String? = null,
    val suboptions: List<SubOption>? = emptyList(),
    val category: String? = null,
    val toggleable: Boolean? = null,
    val damage: DamageType? = null,
    val damageDice: String? = null,
    val damageType: String? = null,
    val overrides: List<String>? = emptyList(),
    val adjustment: Map<String, String>? = emptyMap(),
    val effects: List<RuleEffect>? = emptyList(),
    val range: String? = null,
    @Serializable(with = BsonValueSerializer::class) val predicate: BsonValue? = null,
    val hasHands: Boolean? = null,
    val selector: String? = null,
    val slug: String? = null,
    val choices: List<Choice> = listOf(),
    val flag: String? = null,
    val prompt: String? = null,
    @Serializable(with = BsonValueSerializer::class) val value: BsonValue? = null,
) {
    fun toResponse(): RuleResponse {
        return RuleResponse(
            domain = domain,
            key = key,
            label = label,
            mode = mode,
            type = type,
            option = option,
            suboptions = suboptions,
            category = category,
            toggleable = toggleable,
            damage = damage,
            damageDice = damageDice,
            damageType = damageType,
            overrides = overrides,
            adjustment = adjustment,
            effects = effects,
            range = range,
            predicate = predicate?.transform(),
            hasHands = hasHands,
            selector = selector,
            slug = slug,
            choices = choices,
            flag = flag,
            prompt = prompt,
            value = value?.transform(),
        )
    }

    @Serializable
    data class RuleEffect(
        val affects: String? = null,
        val events: List<String> = emptyList(),
        val includesSelf: Boolean? = null,
        val predicate: List<String> = emptyList(),
        val uuid: String? = null,
    )
}
