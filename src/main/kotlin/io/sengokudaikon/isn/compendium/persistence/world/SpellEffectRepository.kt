package io.sengokudaikon.isn.compendium.persistence.world

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.spell.SpellEffectModel
import io.sengokudaikon.isn.compendium.domain.spell.repository.SpellEffectRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [SpellEffectRepositoryPort::class])
class SpellEffectRepository : BaseRepository<SpellEffectModel>(), SpellEffectRepositoryPort {
    override val modelClass: KClass<SpellEffectModel> = SpellEffectModel::class
    override val collection: MongoCollection<SpellEffectModel> = getCollection<SpellEffectModel>("spell-effects")
}
