package io.sengokudaikon.isn.compendium.domain.character.classs.repository

import io.sengokudaikon.isn.builder.fixtures.model.ClassFeatureFixture
import io.sengokudaikon.isn.compendium.domain.character.classs.entity.ClassFeat
import io.sengokudaikon.isn.infrastructure.repository.RepositoryCreatePort
import io.sengokudaikon.isn.infrastructure.repository.RepositoryOutputPort

interface ClassFeatureRepositoryPort :
    RepositoryOutputPort<ClassFeat>,
    RepositoryCreatePort<ClassFeatureFixture, ClassFeat>
