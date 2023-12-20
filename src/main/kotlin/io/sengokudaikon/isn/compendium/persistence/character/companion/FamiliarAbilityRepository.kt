package io.sengokudaikon.isn.compendium.persistence.character.companion

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.familiar.FamiliarAbilityModel
import io.sengokudaikon.isn.compendium.domain.familiar.repository.FamiliarAbilityRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single(binds = [FamiliarAbilityRepositoryPort::class])
class FamiliarAbilityRepository :
    BaseRepository<FamiliarAbilityModel>(),
    FamiliarAbilityRepositoryPort {
    override val modelClass: KClass<FamiliarAbilityModel> = FamiliarAbilityModel::class
    override val collection: MongoCollection<FamiliarAbilityModel> = getCollection("familiar-abilities")
}
