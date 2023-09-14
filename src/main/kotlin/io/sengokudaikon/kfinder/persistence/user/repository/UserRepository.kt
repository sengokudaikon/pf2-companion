package io.sengokudaikon.kfinder.persistence.user.repository

import arrow.core.Either
import arrow.core.left
import io.sengokudaikon.kfinder.domain.user.entity.User
import io.sengokudaikon.kfinder.domain.user.repository.UserRepositoryPort
import io.sengokudaikon.kfinder.infrastructure.errors.DatabaseException
import io.sengokudaikon.kfinder.operations.user.command.UserCommand
import io.sengokudaikon.kfinder.persistence.user.entity.Users
import io.sengokudaikon.shared.persistence.repository.AbstractRepository
import kotlinx.uuid.UUID
import org.koin.core.annotation.Single

@Single
class UserRepository : AbstractRepository(), UserRepositoryPort {
    override suspend fun findByEmail(email: String): Either<Throwable, User> =
        query { User.find { Users.email eq email }.firstOrNull() }

    override suspend fun findById(id: UUID): Either<Throwable, User> = query { User.findById(id) }

    override suspend fun findByUid(uid: String): Either<Throwable, User> =
        query { User.find { Users.uid eq uid }.firstOrNull() }

    override suspend fun findByUsername(username: String): Either<Throwable, User> =
        query { User.find { Users.username eq username }.firstOrNull() }

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

    override suspend fun findByName(name: String): Either<Throwable, User> =
        query { User.find { Users.username eq name }.firstOrNull() }

    override suspend fun findAll(page: Int, limit: Int): Either<Throwable, List<User>> =
        query { User.all().limit(limit, (page - 1).toLong()).toList() }
}
