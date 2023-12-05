package io.sengokudaikon.isn.compendium.domain.vehicle

import io.sengokudaikon.isn.compendium.domain.Model
import io.sengokudaikon.isn.compendium.domain.system.DescriptionType
import io.sengokudaikon.isn.compendium.domain.system.GenericRule
import io.sengokudaikon.isn.compendium.domain.system.Publication
import io.sengokudaikon.isn.compendium.domain.system.SystemModel
import io.sengokudaikon.isn.compendium.domain.system.Traits
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import org.bson.types.ObjectId

@Serializable
data class VehicleModel(
    @SerialName("_id")
    @Contextual
    override val id: ObjectId,
    override val img: String,
    override val name: String,
    override val type: String,
    override val system: SystemProperty,
    val items: List<ItemModel>,
) : Model {
    override fun getSerializer() = serializer()

    @Serializable
    data class SystemProperty(
        override val description: DescriptionType,
        override val publication: Publication,
        override val traits: Traits,
        override val rules: List<GenericRule>,
        val attributes: Attributes,
        val details: Details,
        val saves: Saves,
    ) : SystemModel {
        @Serializable
        data class Attributes(
            val ac: AC,
            val collisionDC: JsonObject,
            val collisionDamage: JsonObject,
            val hardness: Int,
            val hp: HP,
            val immunities: List<Immunity>,
        ) {
            @Serializable
            data class AC(
                val check: Int,
                val details: String,
                val value: Int,
            )

            @Serializable
            data class HP(
                val details: String,
                val max: Int,
                val temp: Int,
                val value: Int,
            )

            @Serializable
            data class Immunity(
                val type: String,
            )
        }

        @Serializable
        @Suppress("ConstructorParameterNaming")
        data class Details(
            val AC: Int,
            val crew: String,
            val description: JsonObject,
            val level: JsonObject,
            val passengers: String,
            val pilotingCheck: String,
            val price: Int,
            val publication: Publication,
            val space: Space,
        ) {
            @Serializable
            data class Space(
                val high: Int,
                val long: Int,
                val wide: Int,
            )
        }

        @Serializable
        data class Saves(
            val fortitude: Fortitude,
        ) {
            @Serializable
            data class Fortitude(
                val item: Int,
                val rank: Int,
                val saveDetail: String,
                val value: Int,
            )
        }
    }

    @Serializable
    data class ItemModel(
        @SerialName("_id")
        @Contextual
        override val id: ObjectId,
        override val name: String,
        override val type: String,
        override val img: String,
        override val system: SystemProperty,
    ) : Model {
        override fun getSerializer() = serializer()

        @Serializable
        data class SystemProperty(
            override val description: DescriptionType,
            override val publication: Publication,
            override val traits: Traits,
            override val rules: List<GenericRule>,
            val actionType: JsonObject,
            val actions: JsonObject?,
            val category: JsonObject,
            val requirements: JsonObject?,
            val slug: JsonObject?,
            val trigger: JsonObject?,
            val type: String,
        ) : SystemModel
    }
}
