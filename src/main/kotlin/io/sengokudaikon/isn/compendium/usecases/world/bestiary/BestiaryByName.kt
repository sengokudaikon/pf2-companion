package io.sengokudaikon.isn.compendium.usecases.world.bestiary

import io.sengokudaikon.isn.compendium.domain.bestiary.BestiaryModel
import io.sengokudaikon.isn.compendium.domain.bestiary.repository.BestiaryRepositoryPort
import io.sengokudaikon.isn.compendium.operations.world.bestiary.query.BestiaryQuery
import io.sengokudaikon.isn.compendium.ports.world.ByNameBestiaryPort
import io.sengokudaikon.isn.infrastructure.usecases.GetByName
import org.koin.core.annotation.Single

@Single(binds = [ByNameBestiaryPort::class])
class BestiaryByName(override val repository: BestiaryRepositoryPort) :
    GetByName<BestiaryQuery, BestiaryModel>(), ByNameBestiaryPort {
    override fun getCacheKey(query: BestiaryQuery): String {
        query as BestiaryQuery.ByName
        return "model_bestiary:name:${query.name}"
    }
}