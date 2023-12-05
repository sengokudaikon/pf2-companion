package io.sengokudaikon.isn.compendium.persistence.world

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.spell.SpellEffectModel
import io.sengokudaikon.isn.compendium.domain.spell.repository.SpellEffectRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import org.koin.core.annotation.Single

@Single(binds = [SpellEffectRepositoryPort::class])
class SpellEffectRepository : BaseRepository<SpellEffectModel>(SpellEffectModel::class), SpellEffectRepositoryPort {
    override val collection: MongoCollection<SpellEffectModel> = getCollection<SpellEffectModel>("spell-effects")
}
