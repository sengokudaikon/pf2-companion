package io.sengokudaikon.isn.compendium.usecases.world.bestiary

import io.sengokudaikon.isn.compendium.domain.bestiary.BestiaryModel
import io.sengokudaikon.isn.compendium.domain.bestiary.repository.BestiaryRepositoryPort
import io.sengokudaikon.isn.compendium.operations.world.bestiary.query.BestiaryQuery
import io.sengokudaikon.isn.compendium.ports.world.ByIdBestiaryPort
import io.sengokudaikon.isn.infrastructure.usecases.GetById
import org.koin.core.annotation.Single

@Single(binds = [ByIdBestiaryPort::class])
class BestiaryById(override val repository: BestiaryRepositoryPort) :
    GetById<BestiaryQuery, BestiaryModel>(), ByIdBestiaryPort {
    override fun getCacheKey(query: BestiaryQuery): String {
        query as BestiaryQuery.ById
        return "model_bestiary:id:${query.id}"
    }
}