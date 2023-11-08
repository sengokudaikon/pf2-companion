package io.sengokudaikon.dbfinder.operations.character.ancestry.command

import io.sengokudaikon.dbfinder.domain.character.ancestry.model.Ancestry
import io.sengokudaikon.fixtureloader.fixtures.model.AncestryFixture
import io.sengokudaikon.shared.operations.Command

interface AncestryCommand : Command<AncestryFixture, Ancestry> {

    class Create(val dto: AncestryFixture) : AncestryCommand
}
