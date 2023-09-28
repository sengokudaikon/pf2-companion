package io.sengokudaikon.kfinder.persistence.user.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import io.sengokudaikon.kfinder.domain.user.entity.User
import io.sengokudaikon.kfinder.domain.user.repository.UserRepositoryPort
import io.sengokudaikon.kfinder.infrastructure.errors.DatabaseException
import io.sengokudaikon.kfinder.operations.user.command.UserCommand
import io.sengokudaikon.kfinder.persistence.user.entity.Users
import kotlinx.uuid.UUID
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import org.koin.core.annotation.Single

@Single
class UserRepository : UserRepositoryPort {
    override suspend fun findByEmail(email: String): Either<Throwable, User> =
        suspendedTransactionAsync { User.find { Users.email eq email }.firstOrNull() }.await()?.right()
            ?: DatabaseException.NotFound("User not found").left()

    override suspend fun findById(id: UUID): Either<Throwable, User> =
        suspendedTransactionAsync { User.findById(id) }.await()?.right() ?: DatabaseException.NotFound("User not found")
            .left()

    override suspend fun findByUid(uid: String): Either<Throwable, User> =
        suspendedTransactionAsync { User.find { Users.uid eq uid }.firstOrNull() }.await()?.right()
            ?: DatabaseException.NotFound("User not found").left()

    override suspend fun batchInsert(models: Set<UserCommand>) {
        // ignored
    }

    override suspend fun create(command: UserCommand): Either<Throwable, User> = suspendedTransactionAsync {
        command as UserCommand.Create
        User.new {
            this.username = command.username
            this.email = command.email
        }
    }.await().right()

    override suspend fun update(command: UserCommand): Either<Throwable, User> = suspendedTransactionAsync {
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
    }.await()?.right() ?: DatabaseException.NotFound("User not found").left()

    override suspend fun delete(command: UserCommand): Either<Throwable, Boolean> = suspendedTransactionAsync {
        command as UserCommand.Delete
        val user = User.findById(command.id) ?: return@suspendedTransactionAsync false
        user.delete().let { true }
    }.await().right()

    override suspend fun findByName(name: String): Either<Throwable, User> =
        suspendedTransactionAsync { User.find { Users.username eq name }.firstOrNull() }.await()?.right()
            ?: DatabaseException.NotFound("User not found").left()

    override suspend fun findAll(page: Int, limit: Int): Either<Throwable, List<User>> =
        suspendedTransactionAsync { User.all().limit(limit, (page - 1).toLong()).toList() }.await().right()

    override suspend fun findAllNames(): List<String> {
        // ignored
        return emptyList()
    }
}
