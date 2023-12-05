package io.sengokudaikon.isn.compendium.persistence.world

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.spell.SpellModel
import io.sengokudaikon.isn.compendium.domain.spell.repository.SpellRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository

class SpellRepository : BaseRepository<SpellModel>(SpellModel::class), SpellRepositoryPort {
    override val collection: MongoCollection<SpellModel> = getCollection("spells")
}
