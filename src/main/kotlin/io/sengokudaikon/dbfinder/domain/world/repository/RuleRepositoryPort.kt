package io.sengokudaikon.dbfinder.domain.world.repository

import io.sengokudaikon.dbfinder.domain.world.entity.Rule
import io.sengokudaikon.shared.persistence.repository.RepositoryCreatePort
import io.sengokudaikon.shared.persistence.repository.RepositoryOutputPort

interface RuleRepositoryPort :
    RepositoryOutputPort<Rule>,
    RepositoryCreatePort<io.sengokudaikon.dbfinder.domain.world.model.Rule, Rule>
