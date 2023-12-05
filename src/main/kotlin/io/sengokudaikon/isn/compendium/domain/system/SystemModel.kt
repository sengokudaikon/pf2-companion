package io.sengokudaikon.isn.compendium.domain.system

interface SystemModel {
    val description: DescriptionType
    val publication: Publication
    val traits: Traits?
    val rules: List<GenericRule>
}
