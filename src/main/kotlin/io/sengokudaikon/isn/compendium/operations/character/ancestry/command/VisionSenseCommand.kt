package io.sengokudaikon.isn.compendium.operations.character.ancestry.command

import io.sengokudaikon.isn.compendium.enums.VisionType
import io.sengokudaikon.isn.compendium.persistence.character.ancestry.entity.VisionSenses
import kotlinx.serialization.Serializable

interface VisionSenseCommand {
    @Serializable
    data class Create(
        val name: VisionType,
        val range: Int,
        val precision: VisionSenses.Precision,
    ) : VisionSenseCommand
}
