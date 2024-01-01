package io.sengokudaikon.isn.compendium.domain.heritage.repository

import io.sengokudaikon.isn.compendium.domain.heritage.HeritageModel
import io.sengokudaikon.isn.infrastructure.repository.RepositoryOutputPort

interface HeritageRepositoryPort : RepositoryOutputPort<HeritageModel> {
    suspend fun findAllByAncestry(name: String): Result<List<HeritageModel>>
}
