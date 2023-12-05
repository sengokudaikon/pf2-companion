package io.sengokudaikon.isn.compendium.domain.system

import io.sengokudaikon.isn.compendium.enums.ActionTypes
import io.sengokudaikon.isn.compendium.operations.global.response.ActionResponse
import io.sengokudaikon.isn.infrastructure.operations.transform
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.bson.BsonValue
import org.bson.codecs.kotlinx.BsonValueSerializer

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class ActionSystem(
    override val description: DescriptionType,
    override val publication: Publication,
    override val traits: Traits,
    override val rules: List<GenericRule>,
    val frequency: Frequency?,
    val category: String,
    val selfEffect: SelfEffect?,
    @Serializable(with = BsonValueSerializer::class) val prerequisites: BsonValue? = null,
    @Serializable(with = BsonValueSerializer::class) val isDefault: BsonValue? = null,
    @Serializable(with = BsonValueSerializer::class)val actionType: BsonValue? = null,
    @Serializable(with = BsonValueSerializer::class) val actions: BsonValue? = null,
    @Serializable(with = BsonValueSerializer::class) val level: BsonValue? = null,
    @Serializable(with = BsonValueSerializer::class) val maxTakable: BsonValue? = null,
    @Serializable(with = BsonValueSerializer::class) val trigger: BsonValue? = null,
) : SystemModel {
    fun toResponse(): ActionResponse {
        return ActionResponse(
            description = description,
            publication = publication,
            traits = traits.toResponse(),
            rules = rules.map { it.toResponse() },
            frequency = frequency,
            isDefault = isDefault?.transform(),
            actionType = ActionTypes.valueOf(
                actionType?.transform().extractValue()?.uppercase() ?: ActionTypes.NONE.name,
            ),
            actions = actions?.transform().extractValue(),
            category = category,
            level = level?.transform().extractValue()?.toInt(),
            maxTakable = maxTakable?.transform(),
            prerequisites = prerequisites?.transform().extractValue(),
            trigger = trigger?.transform(),
            selfEffect = selfEffect,
        )
    }
}

fun String?.extractValue(): String? {
    return this?.let {
        val jsonObject = Json.parseToJsonElement(it) as JsonObject
        val valueElement = jsonObject["value"]
        when {
            valueElement is JsonArray && valueElement.isEmpty() == true -> ""
            valueElement is JsonNull || valueElement?.jsonPrimitive == null -> ""
            else -> valueElement.jsonPrimitive.content
        }
    }
}
