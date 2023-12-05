package io.sengokudaikon.isn.compendium.domain.publication.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
@Suppress("ConstructorParameterNaming")
data class Book(
    @SerialName("_id")
    @Contextual
    val id: ObjectId,
    val name: String,
    @SerialName("id")
    val shortId: String,
    val source: String,
    val group: String,
    val coverUrl: String,
    val author: String,
    val contents: List<Content>,
) {
    @Serializable
    data class Content(
        val name: String,
        val type: String? = null,
        val entries: List<Entry> = listOf(),
        val headers: List<String> = listOf(),
        val ordinal: Ordinal? = null,
    ) {
        @Serializable
        data class Entry(
            val type: String,
            val href: Href? = null,
            val maxRes: MaxRes? = null,
            val style: String? = null,
        ) {
            @Serializable
            data class Href(
                val type: String,
                val path: String,
                val anchor: String? = null,
            )

            @Serializable
            data class MaxRes(
                val type: String,
                val path: String,
                val anchor: String? = null,
            )
        }

        @Serializable
        data class Ordinal(
            val type: String,
            val identifier: Int
        )
    }
}
