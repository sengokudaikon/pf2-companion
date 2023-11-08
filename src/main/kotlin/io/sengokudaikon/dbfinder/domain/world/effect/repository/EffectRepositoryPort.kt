package io.sengokudaikon.dbfinder.domain.world.effect.repository

import io.sengokudaikon.dbfinder.domain.world.effect.entity.Effect
import io.sengokudaikon.shared.persistence.repository.RepositoryCreatePort
import io.sengokudaikon.shared.persistence.repository.RepositoryOutputPort
import io.sengokudaikon.dbfinder.domain.world.effect.model.Effect as ModelEffect

interface EffectRepositoryPort :
    RepositoryOutputPort<Effect>,
    RepositoryCreatePort<ModelEffect, Effect>
