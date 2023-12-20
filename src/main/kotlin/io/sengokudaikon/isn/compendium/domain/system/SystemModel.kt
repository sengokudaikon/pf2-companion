package io.sengokudaikon.isn.compendium.domain.system

import org.bson.BsonValue

interface SystemModel {
    val description: DescriptionType
    val publication: Publication
    val traits: Traits?
    val rules: BsonValue?
}
