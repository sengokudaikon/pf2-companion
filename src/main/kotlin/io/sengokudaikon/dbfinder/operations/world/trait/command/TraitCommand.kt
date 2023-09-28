package io.sengokudaikon.dbfinder.operations.world.trait.command

import io.sengokudaikon.dbfinder.fixtures.AncestryFixture
import io.sengokudaikon.dbfinder.operations.character.ancestry.command.AncestryCommand
import kotlinx.serialization.Serializable

interface TraitCommand {
    @Serializable
    data class Create(val dto: AncestryFixture) : AncestryCommand
}
