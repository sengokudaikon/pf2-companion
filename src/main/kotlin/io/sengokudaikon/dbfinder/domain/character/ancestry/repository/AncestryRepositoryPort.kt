package io.sengokudaikon.dbfinder.domain.character.ancestry.repository

import io.sengokudaikon.dbfinder.domain.character.ancestry.entity.Ancestry
import io.sengokudaikon.dbfinder.operations.character.ancestry.command.AncestryCommand
import io.sengokudaikon.shared.persistence.repository.RepositoryCreatePort
import io.sengokudaikon.shared.persistence.repository.RepositoryOutputPort

interface AncestryRepositoryPort : RepositoryCreatePort<AncestryCommand, Ancestry>, RepositoryOutputPort<Ancestry>
