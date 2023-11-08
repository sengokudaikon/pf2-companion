package io.sengokudaikon.dbfinder.domain.world.item.repository

import io.sengokudaikon.dbfinder.domain.world.item.entity.Spell
import io.sengokudaikon.shared.persistence.repository.RepositoryCreatePort
import io.sengokudaikon.shared.persistence.repository.RepositoryOutputPort
import io.sengokudaikon.dbfinder.domain.world.item.model.Spell as ModelSpell

interface SpellRepositoryPort : RepositoryOutputPort<Spell>, RepositoryCreatePort<ModelSpell, Spell>
