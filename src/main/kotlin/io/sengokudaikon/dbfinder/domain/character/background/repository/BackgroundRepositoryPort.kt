package io.sengokudaikon.dbfinder.domain.character.background.repository

import io.sengokudaikon.dbfinder.domain.character.background.entity.Background
import io.sengokudaikon.fixtureloader.fixtures.model.BackgroundFixture
import io.sengokudaikon.shared.persistence.repository.RepositoryCreatePort
import io.sengokudaikon.shared.persistence.repository.RepositoryOutputPort

interface BackgroundRepositoryPort :
    RepositoryOutputPort<Background>,
    RepositoryCreatePort<BackgroundFixture, Background>
