package io.sengokudaikon.dbfinder.usecases.world

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import io.sengokudaikon.dbfinder.domain.world.model.Trait
import io.sengokudaikon.dbfinder.domain.world.repository.TraitRepositoryPort
import io.sengokudaikon.dbfinder.operations.world.trait.query.TraitQuery
import io.sengokudaikon.dbfinder.ports.world.TraitPort
import io.sengokudaikon.dbfinder.usecases.CachingUseCase
import kotlinx.serialization.builtins.ListSerializer
import org.koin.core.annotation.Single

@Single(binds = [TraitPort::class])
class TraitUseCase(
    private val repository: TraitRepositoryPort,
) : TraitPort, CachingUseCase() {
    override suspend fun get(query: TraitQuery): Either<Throwable, Trait> {
        query as TraitQuery.FindById
        return repository.findById(query.id).map { it.toModel() }
    }

    override suspend fun getByName(query: TraitQuery): Either<Throwable, Trait> {
        query as TraitQuery.FindByName
        return repository.findByName(query.name).map { it.toModel() }
    }

    override suspend fun list(query: TraitQuery): Either<Throwable, List<Trait>> {
        query as TraitQuery.FindAll
        val cacheKey = "model_trait:all:${query.page}:${query.size}"
        val (_, _, pageStr, limitStr) = cacheKey.split(':')
        val limit = limitStr.toInt()
        return runCatching {
            val modelAncestryList = withCache(cacheKey, ListSerializer(Trait.serializer())) {
                when (val traitResponse = repository.findAll(pageStr.toInt(), limit)) {
                    is Either.Right -> {
                        traitResponse.value.map { it.toModel() }
                    }

                    is Either.Left -> {
                        throw traitResponse.value
                    }
                }
            }
            modelAncestryList.right()
        }.getOrElse {
            it.left()
        }
    }
}
