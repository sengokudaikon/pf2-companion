package io.sengokudaikon.isn.compendium.domain.condition

import io.sengokudaikon.isn.compendium.domain.Model
import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.GenericRule
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.domain.system.SystemModel
import io.sengokudaikon.isn.compendium.domain.system.Traits
import kotlinx.serialization.Contextual
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import org.bson.types.ObjectId

@Serializable
@Suppress("ConstructorParameterNaming")
data class ConditionModel(
    @SerialName("_id")
    @Contextual
    override val id: ObjectId,
    override val img: String,
    override val name: String,
    override val type: String,
    override val system: SystemProperty,
) : Model {
    @Serializable
    data class SystemProperty(
        override val description: DescriptionType,
        val duration: JsonObject,
        val group: String,
        val overrides: List<String>,
        override val publication: Publication,
        val references: References,
        override val rules: List<GenericRule>,
        override val traits: Traits,
    ) : SystemModel {

        @Serializable
        data class References(
            val children: List<String>,
            val immunityFrom: List<String>,
            val overriddenBy: List<String>,
            val overrides: List<String>,
        )
    }

    override fun getSerializer(): KSerializer<*> = serializer()
}
