package io.sengokudaikon.dbfinder.domain.character.companion.entity

import io.sengokudaikon.dbfinder.persistence.character.companion.entity.FamiliarAbilities
import io.sengokudaikon.dbfinder.persistence.character.companion.entity.Familiars
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import io.sengokudaikon.dbfinder.domain.character.companion.model.Familiar as ModelFamiliar

class Familiar(id: EntityID<UUID>) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<Familiar>(Familiars)

    var name by Familiars.name
    var description by Familiars.description
    var hp by Familiars.hp
    var rarity by Familiars.rarity
    var type by Familiars.type
    val abilities by FamiliarAbility referrersOn FamiliarAbilities.familiar

    suspend fun toModel(): ModelFamiliar {
        return suspendedTransactionAsync {
            ModelFamiliar(
                name = name,
                description = description,
                hp = hp,
                rarity = rarity.name,
                type = type,
                abilities = abilities.map { it.toModel() },
            )
        }.await()
    }
}
