package io.sengokudaikon.isn.compendium.usecases.world.bestiary

import io.sengokudaikon.isn.compendium.domain.bestiary.BestiaryModel
import io.sengokudaikon.isn.compendium.domain.bestiary.repository.BestiaryRepositoryPort
import io.sengokudaikon.isn.compendium.operations.world.bestiary.query.BestiaryQuery
import io.sengokudaikon.isn.compendium.ports.world.ListBestiaryPort
import io.sengokudaikon.isn.infrastructure.usecases.GetList
import org.koin.core.annotation.Single

@Single(binds = [ListBestiaryPort::class])
class BestiaryList(override val repository: BestiaryRepositoryPort) :
    GetList<BestiaryQuery, BestiaryModel>(), ListBestiaryPort {
    override fun getCacheKey(query: BestiaryQuery): String {
        query as BestiaryQuery.All
        return "model_bestiary:all:${query.page}:${query.size}"
    }
}