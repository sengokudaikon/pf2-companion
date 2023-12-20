package io.sengokudaikon.isn.compendium.domain.system

import io.sengokudaikon.isn.compendium.infrastructure.mapper.extractValue
import io.sengokudaikon.isn.compendium.operations.global.dto.DamageType
import io.sengokudaikon.isn.infrastructure.operations.response.RuleResponse
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
    @Serializable(with = BsonValueSerializer::class) val suboptions: BsonValue? = null,
    val category: String? = null,
    val toggleable: Boolean? = null,
    val damage: DamageType? = null,
    val damageDice: String? = null,
    val damageType: String? = null,
    @Serializable(with = BsonValueSerializer::class) val overrides: BsonValue? = null,
    @Serializable(with = BsonValueSerializer::class) val adjustment: BsonValue? = null,
    @Serializable(with = BsonValueSerializer::class) val effects: BsonValue? = null,
    val range: String? = null,
    @Serializable(with = BsonValueSerializer::class) val predicate: BsonValue? = null,
    val hasHands: Boolean? = null,
    @Serializable(with = BsonValueSerializer::class) val selector: BsonValue? = null,
    val slug: String? = null,
    @Serializable(with = BsonValueSerializer::class) val choices: BsonValue? = null,
    val flag: String? = null,
    val prompt: String? = null,
    @Serializable(with = BsonValueSerializer::class) val value: BsonValue? = null,
) {
    fun toResponse(): RuleResponse {
        val response = RuleResponse(
            domain = domain,
            key = key,
            label = label,
            mode = mode,
            type = type,
            option = option,
            suboptions = suboptions?.transform().extractValue(),
            category = category,
            toggleable = toggleable,
            damage = damage,
            damageDice = damageDice,
            damageType = damageType,
            overrides = overrides?.transform().extractValue(),
            adjustment = adjustment?.transform().extractValue(),
            effects = effects?.transform().extractValue(),
            range = range,
            predicate = predicate?.transform().extractValue(),
            hasHands = hasHands,
            selector = selector?.transform(),
            slug = slug,
            choices = choices?.transform().extractValue(),
            flag = flag,
            prompt = prompt,
            value = value?.transform().extractValue(),
        )
        return response
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
