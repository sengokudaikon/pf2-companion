package io.sengokudaikon.isn.compendium.domain.effects.model

import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.domain.system.SystemModel
import io.sengokudaikon.isn.compendium.domain.system.TokenIcon
import io.sengokudaikon.isn.compendium.domain.system.Traits
import io.sengokudaikon.isn.compendium.domain.system.Value
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import org.bson.BsonValue
import org.bson.codecs.kotlinx.BsonValueSerializer

@Serializable
@OptIn(ExperimentalSerializationApi::class)
data class EffectSystem(
    override val description: DescriptionType,
    override val publication: Publication,
    override val traits: Traits,
    @Serializable(with = BsonValueSerializer::class) override val rules: BsonValue? = null,
    @Serializable(with = BsonValueSerializer::class) val badge: BsonValue? = null,
    @Serializable(with = BsonValueSerializer::class) val duration: BsonValue? = null,
    @Serializable(with = BsonValueSerializer::class) val level: BsonValue,
    @Serializable(with = BsonValueSerializer::class) val start: BsonValue? = null,
    val tokenIcon: TokenIcon,
) : SystemModel {
    @Serializable
    data class Badge(
        val max: Int? = null,
        val min: Int? = null,
        val evaluate: Boolean? = null,
        val reevaluate: String? = null,
        val labels: List<String> = listOf(),
        val type: String? = null,
        @Serializable val value: Value,
    )
}
