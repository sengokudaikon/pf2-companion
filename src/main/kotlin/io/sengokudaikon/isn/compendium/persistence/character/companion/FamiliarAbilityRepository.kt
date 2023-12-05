package io.sengokudaikon.isn.compendium.persistence.character.companion

import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.compendium.domain.familiar.FamiliarAbilityModel
import io.sengokudaikon.isn.compendium.domain.familiar.repository.FamiliarAbilityRepositoryPort
import io.sengokudaikon.isn.infrastructure.getCollection
import io.sengokudaikon.isn.infrastructure.repository.BaseRepository

class FamiliarAbilityRepository :
    BaseRepository<FamiliarAbilityModel>(FamiliarAbilityModel::class),
    FamiliarAbilityRepositoryPort {
    override val collection: MongoCollection<FamiliarAbilityModel> = getCollection("familiar-abilities")
}
