package io.sengokudaikon.isn.compendium.operations.global.vo

import io.sengokudaikon.isn.compendium.enums.Proficiency
import kotlinx.serialization.Serializable

@Serializable
sealed class Prerequisite {
    abstract val value: String

    @Serializable
    data class Feat(override val value: String) : Prerequisite()

    @Serializable
    data class Dedication(override val value: String) : Prerequisite()

    @Serializable
    data class Skill(val level: Proficiency, override val value: String) : Prerequisite()
}
