package io.sengokudaikon.buildfinder.persistence.entity

import kotlinx.uuid.exposed.KotlinxUUIDTable

object Notes : KotlinxUUIDTable("notes") {
    val characterID = reference("characterID", Characters)
    val note = text("note")
    val placeholderText = text("placeholderText")
}
