package io.sengokudaikon.isn.compendium.operations.character.ancestry.command

import io.sengokudaikon.isn.builder.fixtures.model.AncestryFixture
import io.sengokudaikon.isn.compendium.domain.character.ancestry.model.Ancestry
import io.sengokudaikon.isn.infrastructure.operations.Command

interface AncestryCommand : Command<AncestryFixture, Ancestry> {

    class Create(val dto: AncestryFixture) : AncestryCommand
}
