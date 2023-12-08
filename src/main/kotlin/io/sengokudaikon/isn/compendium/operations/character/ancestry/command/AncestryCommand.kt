package io.sengokudaikon.isn.compendium.operations.character.ancestry.command

import io.sengokudaikon.isn.compendium.domain.ancestry.AncestryModel
import io.sengokudaikon.isn.infrastructure.operations.Command

interface AncestryCommand : Command {

    class Create(val dto: AncestryModel) : AncestryCommand
}
