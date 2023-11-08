package io.sengokudaikon.dbfinder.domain.character.classs.repository

import io.sengokudaikon.dbfinder.domain.character.classs.entity.Class
import io.sengokudaikon.fixtureloader.fixtures.model.ClassFixture
import io.sengokudaikon.shared.persistence.repository.RepositoryCreatePort
import io.sengokudaikon.shared.persistence.repository.RepositoryOutputPort

interface ClassRepositoryPort : RepositoryOutputPort<Class>, RepositoryCreatePort<ClassFixture, Class>
