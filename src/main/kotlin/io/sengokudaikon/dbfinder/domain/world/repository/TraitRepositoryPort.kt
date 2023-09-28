package io.sengokudaikon.dbfinder.domain.world.repository

import io.sengokudaikon.dbfinder.domain.world.entity.Trait
import io.sengokudaikon.dbfinder.fixtures.TraitFixture
import io.sengokudaikon.shared.persistence.repository.RepositoryCreatePort
import io.sengokudaikon.shared.persistence.repository.RepositoryOutputPort

interface TraitRepositoryPort : RepositoryCreatePort<TraitFixture, Trait>, RepositoryOutputPort<Trait>
