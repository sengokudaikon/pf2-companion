package io.sengokudaikon.dbfinder.operations.character.ancestry.command

import io.sengokudaikon.dbfinder.infrastructure.enums.VisionType
import io.sengokudaikon.dbfinder.persistence.character.ancestry.entity.VisionSenses
import kotlinx.serialization.Serializable

interface VisionSenseCommand {
    @Serializable
    data class Create(
        val name: VisionType,
        val range: Int,
        val precision: VisionSenses.Precision,
    ) : VisionSenseCommand
}
