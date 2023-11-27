package io.sengokudaikon.isn.compendium.usecases.character

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import io.sengokudaikon.isn.compendium.domain.character.ancestry.model.Ancestry
import io.sengokudaikon.isn.compendium.domain.character.ancestry.repository.AncestryRepositoryPort
import io.sengokudaikon.isn.compendium.operations.character.ancestry.query.AncestryQuery
import io.sengokudaikon.isn.compendium.ports.character.AncestryPort
import io.sengokudaikon.isn.compendium.usecases.CachingUseCase
import kotlinx.serialization.builtins.ListSerializer
import org.koin.core.annotation.Single

@Single(binds = [AncestryPort::class])
class AncestryUseCase(
    private val ancestryRepository: AncestryRepositoryPort,
) : AncestryPort, CachingUseCase() {
    override suspend fun get(query: AncestryQuery): Either<Throwable, Ancestry> {
        query as AncestryQuery.FindById
        return ancestryRepository.findById(query.id).map { it.toModel() }
    }

    override suspend fun list(query: AncestryQuery): Either<Throwable, List<Ancestry>> {
        query as AncestryQuery.FindAll
        val cacheKey = "model_ancestry:all:${query.page}:${query.limit}"
        return runCatching {
            val modelAncestryList = withCache(cacheKey, ListSerializer(Ancestry.serializer())) {
                when (val ancestryResponse = ancestryRepository.findAll(query.page, query.limit)) {
                    is Either.Right -> {
                        ancestryResponse.value.map { it.toModel() }
                    }

                    is Either.Left -> {
                        throw ancestryResponse.value
                    }
                }
            }
            modelAncestryList.right()
        }.getOrElse {
            it.left()
        }
    }

    override suspend fun getByName(query: AncestryQuery): Either<Throwable, Ancestry> {
        query as AncestryQuery.FindByName
        return ancestryRepository.findByName(query.name).map { it.toModel() }
    }
}
