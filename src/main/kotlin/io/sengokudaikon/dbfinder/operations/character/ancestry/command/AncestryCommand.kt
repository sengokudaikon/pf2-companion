package io.sengokudaikon.dbfinder.operations.character.ancestry.command

import io.sengokudaikon.dbfinder.domain.character.ancestry.model.Ancestry
import io.sengokudaikon.dbfinder.domain.character.ancestry.model.AncestryDTO
import io.sengokudaikon.shared.operations.Command

interface AncestryCommand : Command<AncestryDTO, Ancestry> {
    val dto: AncestryDTO

    class Create(override val dto: AncestryDTO) : AncestryCommand
}
