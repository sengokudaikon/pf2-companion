package io.sengokudaikon.isn.compendium.domain.world.item.repository

import io.sengokudaikon.isn.compendium.domain.world.item.entity.Spell
import io.sengokudaikon.isn.infrastructure.repository.RepositoryCreatePort
import io.sengokudaikon.isn.infrastructure.repository.RepositoryOutputPort
import io.sengokudaikon.isn.compendium.domain.world.item.model.Spell as ModelSpell

interface SpellRepositoryPort : RepositoryOutputPort<Spell>, RepositoryCreatePort<ModelSpell, Spell>
