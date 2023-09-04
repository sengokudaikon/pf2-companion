package io.sengokudaikon.domain.user.entity

import io.sengokudaikon.persistence.user.entity.Users
import kotlinx.uuid.UUID
import kotlinx.uuid.exposed.KotlinxUUIDEntity
import kotlinx.uuid.exposed.KotlinxUUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class User(
    id: EntityID<UUID>,
): KotlinxUUIDEntity(id) {
    companion object : KotlinxUUIDEntityClass<User>(Users)
    var uid by Users.uid
    var email by Users.email
    var username by Users.username
    var role by Users.role
    var created by Users.createdAt
    var updated by Users.updatedAt

    fun toModel(): io.sengokudaikon.domain.user.model.User {
        return io.sengokudaikon.domain.user.model.User(
            id = this.id.value.toString(),
            username = this.username,
            email = this.email,
            role = this.role,
        )
    }
}
