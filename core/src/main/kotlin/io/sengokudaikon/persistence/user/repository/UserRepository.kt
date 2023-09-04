package io.sengokudaikon.persistence.user.repository

import arrow.core.Either
import arrow.core.left
import io.sengokudaikon.domain.user.entity.User
import io.sengokudaikon.infrastructure.errors.DatabaseException
import io.sengokudaikon.operations.user.command.UserCommand
import io.sengokudaikon.persistence.repository.AbstractRepository
import io.sengokudaikon.persistence.user.entity.Users
import io.sengokudaikon.ports.user.repository.UserRepositoryPort
import kotlinx.uuid.UUID

class UserRepository:AbstractRepository(), UserRepositoryPort {
    override suspend fun findByEmail(email: String): Either<Throwable, User> =
        query { User.find { Users.email eq email }.firstOrNull() }

    override suspend fun findById(id: UUID): Either<Throwable, User> = query { User.findById(id) }

    override suspend fun findByUid(uid: String): Either<Throwable, User> =
        query { User.find { Users.uid eq uid }.firstOrNull() }

    override suspend fun create(command: UserCommand): Either<Throwable, User> = query {
        command as UserCommand.Create
        User.new {
            this.username = command.username
            this.email = command.email
        }
    }

    override suspend fun update(command: UserCommand): Either<Throwable, User> = query {
        command as UserCommand.Update
        val user = User.findById(command.id)

        if (user == null) {
            DatabaseException.NotFound("User not found").left()
        }
        user?.apply {
            this.username = command.username ?: this.username
            this.email = command.email ?: this.email
        }
        user
    }

    override suspend fun delete(command: UserCommand): Either<Throwable, Boolean> = query {
        command as UserCommand.Delete
        val user = User.findById(command.id) ?: return@query false
        user.delete().let { true }
    }

    override suspend fun findAll(): Either<Throwable, List<User>> = query { User.all().toList() }
}
