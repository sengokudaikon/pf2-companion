package io.sengokudaikon.dbfinder.ports.character

import io.sengokudaikon.dbfinder.domain.character.ancestry.model.Ancestry
import io.sengokudaikon.dbfinder.operations.character.ancestry.command.AncestryCommand
import io.sengokudaikon.dbfinder.operations.character.ancestry.query.AncestryQuery
import io.sengokudaikon.shared.ports.CrudPort

interface AncestryPort : CrudPort<AncestryCommand, AncestryQuery, Ancestry>
