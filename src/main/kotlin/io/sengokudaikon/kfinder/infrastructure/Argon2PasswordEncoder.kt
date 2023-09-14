package io.sengokudaikon.kfinder.infrastructure

import org.bouncycastle.crypto.generators.Argon2BytesGenerator
import org.bouncycastle.crypto.params.Argon2Parameters
import org.koin.core.annotation.Single
import java.security.SecureRandom
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@Single
class Argon2PasswordEncoder {
    companion object {
        private const val SALT_LENGTH = 16
        private const val HASH_LENGTH = 32
    }

    @OptIn(ExperimentalEncodingApi::class)
    fun encode(plainPassword: String): String {
        val salt = ByteArray(SALT_LENGTH).also { SecureRandom().nextBytes(it) }
        val hash = ByteArray(HASH_LENGTH)

        val params = Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
            .withSalt(salt)
            .withParallelism(1)
            .withIterations(4)
            .withMemoryAsKB(65536)
            .build()

        val generator = Argon2BytesGenerator()
        generator.init(params)
        generator.generateBytes(plainPassword.toByteArray(), hash)

        return Base64.encode(salt + hash)
    }

    @OptIn(ExperimentalEncodingApi::class)
    fun verify(plainPassword: String, encodedPassword: String): Boolean {
        val decoded = Base64.decode(encodedPassword)
        val salt = decoded.copyOfRange(0, SALT_LENGTH)
        val storedHash = decoded.copyOfRange(SALT_LENGTH, SALT_LENGTH + HASH_LENGTH)

        val params = Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
            .withSalt(salt)
            .withParallelism(1)
            .withMemoryAsKB(65536)
            .withIterations(4)
            .build()

        val generator = Argon2BytesGenerator()
        generator.init(params)
        val computedHash = ByteArray(HASH_LENGTH)
        generator.generateBytes(plainPassword.toByteArray(), computedHash)

        return storedHash.contentEquals(computedHash)
    }
}
