package io.sengokudaikon.dbfinder.domain.character.classs.repository

import io.sengokudaikon.dbfinder.domain.character.classs.entity.ClassFeat
import io.sengokudaikon.fixtureloader.fixtures.model.ClassFeatureFixture
import io.sengokudaikon.shared.persistence.repository.RepositoryCreatePort
import io.sengokudaikon.shared.persistence.repository.RepositoryOutputPort

interface ClassFeatureRepositoryPort :
    RepositoryOutputPort<ClassFeat>,
    RepositoryCreatePort<ClassFeatureFixture, ClassFeat>
