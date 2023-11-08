package io.sengokudaikon.dbfinder.domain.world.action.repository

import io.sengokudaikon.dbfinder.domain.world.action.entity.Action
import io.sengokudaikon.fixtureloader.fixtures.model.ActionFixture
import io.sengokudaikon.shared.persistence.repository.RepositoryCreatePort
import io.sengokudaikon.shared.persistence.repository.RepositoryOutputPort

interface ActionRepositoryPort :
    RepositoryOutputPort<Action>,
    RepositoryCreatePort<ActionFixture, Action>
