package io.sengokudaikon.isn.compendium.domain.world.action.repository

import io.sengokudaikon.isn.builder.fixtures.model.ActionFixture
import io.sengokudaikon.isn.compendium.domain.world.action.entity.Action
import io.sengokudaikon.isn.infrastructure.repository.RepositoryCreatePort
import io.sengokudaikon.isn.infrastructure.repository.RepositoryOutputPort

interface ActionRepositoryPort :
    RepositoryOutputPort<Action>,
    RepositoryCreatePort<ActionFixture, Action>
