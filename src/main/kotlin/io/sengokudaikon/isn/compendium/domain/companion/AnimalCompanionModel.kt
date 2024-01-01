package io.sengokudaikon.isn.compendium.domain.companion

import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryModel
import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.Languages
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.domain.system.SystemModel
import io.sengokudaikon.isn.compendium.domain.system.Traits
import io.sengokudaikon.isn.infrastructure.domain.Model
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.BsonValue
import org.bson.codecs.kotlinx.BsonValueSerializer
import org.bson.codecs.kotlinx.ObjectIdSerializer
import org.bson.types.ObjectId

@OptIn(ExperimentalSerializationApi::class)
@Serializable
class AnimalCompanionModel(
    @SerialName("_id")
    @Serializable(with = ObjectIdSerializer::class)
    override val id: ObjectId,
    override val img: String,
    override val name: String,
    override val type: String,
    override val system: AnimalCompanionSystem
) : Model {
    @Serializable
    data class AnimalCompanionSystem(
        override val description: DescriptionType,
        override val publication: Publication = Publication("ORC", false, "CRB"),
        override val traits: Traits?,
        @OptIn(ExperimentalSerializationApi::class)
        @Serializable(with = BsonValueSerializer::class) override val rules: BsonValue? = null,
        val boosts: Map<String, AncestryModel.SystemProperty.AbilityScore>,
        val flaws: Map<String, AncestryModel.SystemProperty.AbilityScore>,
        val reach: Int?,
        val size: String?,
        val speed: Int?,
        val vision: String?,
        val languages: Languages?,
        val additionalLanguages: Languages?,
        ) : SystemModel
}
