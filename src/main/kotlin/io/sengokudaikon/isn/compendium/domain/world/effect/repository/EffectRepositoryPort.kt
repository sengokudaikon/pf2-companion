package io.sengokudaikon.isn.compendium.domain.world.effect.repository

import io.sengokudaikon.isn.compendium.domain.world.effect.entity.Effect
import io.sengokudaikon.isn.infrastructure.repository.RepositoryCreatePort
import io.sengokudaikon.isn.infrastructure.repository.RepositoryOutputPort
import io.sengokudaikon.isn.compendium.domain.world.effect.model.Effect as ModelEffect

interface EffectRepositoryPort :
    RepositoryOutputPort<Effect>,
    RepositoryCreatePort<ModelEffect, Effect>
