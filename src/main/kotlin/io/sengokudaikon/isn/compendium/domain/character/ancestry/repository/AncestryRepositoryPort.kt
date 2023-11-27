package io.sengokudaikon.isn.compendium.domain.character.ancestry.repository

import io.sengokudaikon.isn.compendium.domain.character.ancestry.entity.Ancestry
import io.sengokudaikon.isn.compendium.operations.character.ancestry.command.AncestryCommand
import io.sengokudaikon.isn.infrastructure.repository.RepositoryCreatePort
import io.sengokudaikon.isn.infrastructure.repository.RepositoryOutputPort

interface AncestryRepositoryPort : RepositoryCreatePort<AncestryCommand, Ancestry>, RepositoryOutputPort<Ancestry>
