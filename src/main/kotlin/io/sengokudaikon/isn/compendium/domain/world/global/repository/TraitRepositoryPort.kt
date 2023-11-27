package io.sengokudaikon.isn.compendium.domain.world.global.repository

import io.sengokudaikon.isn.builder.fixtures.model.TraitFixture
import io.sengokudaikon.isn.compendium.domain.world.global.entity.Trait
import io.sengokudaikon.isn.infrastructure.repository.RepositoryCreatePort
import io.sengokudaikon.isn.infrastructure.repository.RepositoryOutputPort

interface TraitRepositoryPort : RepositoryCreatePort<TraitFixture, Trait>, RepositoryOutputPort<Trait>
