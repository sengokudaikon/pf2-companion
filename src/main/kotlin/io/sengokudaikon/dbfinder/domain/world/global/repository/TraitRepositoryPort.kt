package io.sengokudaikon.dbfinder.domain.world.global.repository

import io.sengokudaikon.dbfinder.domain.world.global.entity.Trait
import io.sengokudaikon.fixtureloader.fixtures.model.TraitFixture
import io.sengokudaikon.shared.persistence.repository.RepositoryCreatePort
import io.sengokudaikon.shared.persistence.repository.RepositoryOutputPort

interface TraitRepositoryPort : RepositoryCreatePort<TraitFixture, Trait>, RepositoryOutputPort<Trait>
