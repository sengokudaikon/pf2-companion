package io.sengokudaikon.dbfinder.fixtures

import io.sengokudaikon.dbfinder.domain.world.model.Rule
import io.sengokudaikon.dbfinder.domain.world.model.RuleChoice
import io.sengokudaikon.dbfinder.infrastructure.enums.RuleMode
import kotlinx.serialization.Serializable

@Serializable
data class JsonRule(
    val key: String,
    val mode: String,
    val priority: Int? = null,
    val prompt: String? = null,
    val values: Map<String, String>,
    val description: String? = null,
    val path: String? = null,
) {
    fun toModel(): Rule {
        return Rule(
            name = key,
            mode = RuleMode.valueOf(mode.uppercase()),
            priority = priority ?: 0,
            prompt = prompt ?: "",
            ruleChoices = values.map { (key, value) ->
                RuleChoice(
                    name = key,
                    value = value,
                )
            }.toList(),
            description = description,
            contentSrc = path ?: "",
        )
    }
}
