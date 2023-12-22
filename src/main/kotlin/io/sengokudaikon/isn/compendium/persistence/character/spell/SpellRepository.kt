package io.sengokudaikon.isn.compendium.persistence.character.spell

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.spell.SpellModel
import io.sengokudaikon.isn.compendium.domain.spell.repository.SpellRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [SpellRepositoryPort::class])
class SpellRepository : BaseRepository<SpellModel>(), SpellRepositoryPort {
    override val modelClass: KClass<SpellModel> = SpellModel::class
    override val collection: MongoCollection<SpellModel> = getCollection("spells")
}
