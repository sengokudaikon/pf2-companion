package io.sengokudaikon.kfinder.domain.user.entity

import io.sengokudaikon.kfinder.persistence.user.entity.UserTokens
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserToken(
    id: EntityID<UUID>,
) : KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<UserToken>(UserTokens)
    var userId by UserTokens.userId
    var token by UserTokens.token
}
