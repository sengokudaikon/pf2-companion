package io.sengokudaikon.isn.compendium.domain.spell

import io.sengokudaikon.isn.compendium.domain.effects.model.EffectSystem
import io.sengokudaikon.isn.infrastructure.domain.Model
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class SpellEffectModel(
    @SerialName("_id")
    @Contextual
    override val id: ObjectId,
    override val img: String,
    override val name: String,
    override val type: String,
    override val system: EffectSystem,
) : Model
