package io.sengokudaikon.isn.app.persistence.user.repository

import com.mongodb.client.model.Filters.eq
import com.mongodb.kotlin.client.coroutine.MongoCollection
import io.sengokudaikon.isn.app.domain.user.User
import io.sengokudaikon.isn.app.domain.user.repository.UserRepositoryPort
import io.sengokudaikon.isn.app.operations.user.command.UserCommand
import io.sengokudaikon.isn.compendium.operations.search.dto.Filter
import io.sengokudaikon.isn.compendium.operations.search.dto.Sort
import io.sengokudaikon.isn.infrastructure.errors.DatabaseException
import io.sengokudaikon.isn.infrastructure.getCollection
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.types.ObjectId
import org.koin.core.annotation.Single
import kotlin.reflect.KCallable

@Single
class UserRepository : UserRepositoryPort {
    override val collection: MongoCollection<User> = getCollection<User>("users")
    override suspend fun findByEmail(email: String): Result<User> = find(User::email, email)

    override suspend fun findById(id: String, filters: List<Filter>): Result<User> = find(User::id, id)

    override suspend fun findByUid(uid: String): Result<User> = find(User::uid, uid)

    override suspend fun findByUsername(username: String): Result<User> = find(User::name, username)

    override suspend fun create(command: UserCommand, uid: String): Result<User> = runCatching {
        command as UserCommand.Auth.Register
        val user = User(
            id = ObjectId().toString(),
            email = command.email,
            uid = uid,
            role = command.role,
            name = command.username,
        )
        collection.insertOne(user)
        user
    }

    override suspend fun update(command: UserCommand, uid: String): Result<User> = runCatching {
        command as UserCommand.Auth.Update
        val user = collection.find<User>(eq(User::id.name, command.id)).firstOrNull()
            ?: throw DatabaseException.NotFound(User::class.qualifiedName)
        val updatedUser = user.copy(
            role = command.role ?: user.role,
        )
        collection.replaceOne(eq(User::id.name, command.id), updatedUser)
        updatedUser
    }

    override suspend fun delete(command: UserCommand, uid: String): Result<Boolean> = runCatching {
        command as UserCommand.Auth.Delete
        val deleteResult = collection.deleteOne(eq(User::id.name, command.id))
        (deleteResult.deletedCount > 0)
    }

    override suspend fun findByName(name: String, filters: List<Filter>): Result<User> = find(User::name, name)

    override suspend fun findAll(page: Int, limit: Int, filters: List<Filter>, sort: List<Sort>): Result<List<User>> = runCatching {
        collection.find().skip((page - 1) * limit).limit(limit).toList()
    }

    override suspend fun findByNames(names: List<String>): Result<List<User>> = runCatching {
        collection.find().filter { it.name in names }.toList()
    }

    suspend fun <R> find(field: KCallable<R>, value: R): Result<User> {
        return runCatching {
            collection.find(eq(field.name, value)).firstOrNull()
                ?: return Result.failure(DatabaseException.NotFound(User::class.qualifiedName))
        }
    }
}
