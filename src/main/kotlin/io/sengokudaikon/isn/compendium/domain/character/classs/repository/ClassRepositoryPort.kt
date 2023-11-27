package io.sengokudaikon.isn.compendium.domain.character.classs.repository

import io.sengokudaikon.isn.builder.fixtures.model.ClassFixture
import io.sengokudaikon.isn.compendium.domain.character.classs.entity.Class
import io.sengokudaikon.isn.infrastructure.repository.RepositoryCreatePort
import io.sengokudaikon.isn.infrastructure.repository.RepositoryOutputPort

interface ClassRepositoryPort : RepositoryOutputPort<Class>, RepositoryCreatePort<ClassFixture, Class>
