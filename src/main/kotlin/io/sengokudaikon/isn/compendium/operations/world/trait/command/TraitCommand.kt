package io.sengokudaikon.isn.compendium.operations.world.trait.command

import io.sengokudaikon.isn.builder.fixtures.model.AncestryFixture
import io.sengokudaikon.isn.compendium.operations.character.ancestry.command.AncestryCommand
import kotlinx.serialization.Serializable

interface TraitCommand {
    @Serializable
    data class Create(val dto: AncestryFixture) : AncestryCommand
}
