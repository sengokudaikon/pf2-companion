package io.sengokudaikon.isn.compendium.operations.global.command

import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryModel
import io.sengokudaikon.isn.compendium.operations.character.ancestry.command.AncestryCommand
import kotlinx.serialization.Serializable

interface TraitCommand {
    @Serializable
    data class Create(val dto: AncestryModel) : AncestryCommand
}
