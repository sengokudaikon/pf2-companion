package io.sengokudaikon.isn.compendium.domain.character.background.repository

import io.sengokudaikon.isn.builder.fixtures.model.BackgroundFixture
import io.sengokudaikon.isn.compendium.domain.character.background.entity.Background
import io.sengokudaikon.isn.infrastructure.repository.RepositoryCreatePort
import io.sengokudaikon.isn.infrastructure.repository.RepositoryOutputPort

interface BackgroundRepositoryPort :
    RepositoryOutputPort<Background>,
    RepositoryCreatePort<BackgroundFixture, Background>
