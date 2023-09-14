package io.sengokudaikon.dbfinder.usecases.character

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import io.sengokudaikon.dbfinder.domain.character.ancestry.cache.AncestryModelCache
import io.sengokudaikon.dbfinder.domain.character.ancestry.model.Ancestry
import io.sengokudaikon.dbfinder.domain.character.ancestry.repository.AncestryRepositoryPort
import io.sengokudaikon.dbfinder.operations.character.ancestry.command.AncestryCommand
import io.sengokudaikon.dbfinder.operations.character.ancestry.query.AncestryQuery
import io.sengokudaikon.dbfinder.ports.character.AncestryPort
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Single

@Single(binds = [AncestryPort::class])
class AncestryUseCase(
    private val ancestryRepository: AncestryRepositoryPort,
) : AncestryPort {
    override suspend fun create(command: AncestryCommand): Either<Throwable, Ancestry> {
        TODO("Not yet implemented")
    }

    override suspend fun update(command: AncestryCommand): Either<Throwable, Ancestry> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(command: AncestryCommand): Either<Throwable, Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun get(query: AncestryQuery): Either<Throwable, Ancestry> {
        query as AncestryQuery.FindByName
        return ancestryRepository.findByName(query.name).map { it.toModel() }
    }

    override suspend fun list(query: AncestryQuery): Either<Throwable, List<Ancestry>> {
        query as AncestryQuery.FindAll
        val cacheKey = "model_ancestry:all:${query.page}:${query.size}"
        return runCatching {
            val modelAncestryList = withContext(Dispatchers.IO) {
                AncestryModelCache.cache.get(cacheKey).get()
            }
            modelAncestryList.right()
        }.getOrElse {
            it.left()
        }
    }
}
