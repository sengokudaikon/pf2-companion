package io.sengokudaikon.isn.compendium.domain.background

import io.sengokudaikon.isn.compendium.domain.action.ActionModel
import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryModel
import io.sengokudaikon.isn.compendium.domain.feat.FeatModel
import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.Item
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

@Suppress("ConstructorParameterNaming")
@Serializable()
@OptIn(ExperimentalSerializationApi::class)
data class BackgroundModel(
    @SerialName("_id")
    @Serializable(with = ObjectIdSerializer::class)
    override val id: ObjectId,
    override val img: String,
    override val name: String,
    override val type: String,
    override val system: SystemProperty,
) : Model {
    var feats: Map<String, FeatModel> = emptyMap()
    var actions: Map<String, ActionModel> = emptyMap()

    @Serializable
    data class SystemProperty(
        override val description: DescriptionType,
        @OptIn(ExperimentalSerializationApi::class)
        @Serializable(with = BsonValueSerializer::class) override val rules: BsonValue? = null,
        override val publication: Publication,
        override val traits: Traits,
        val boosts: Map<String, AncestryModel.SystemProperty.AbilityScore>,
        val items: Map<String, Item>,
        val trainedLore: String,
        val trainedSkills: TrainedSkills,
    ) : SystemModel {
        @Serializable
        data class TrainedSkills(
            val value: List<String>
        )
    }
}
