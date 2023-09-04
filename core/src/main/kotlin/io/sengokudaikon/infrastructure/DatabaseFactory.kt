package io.sengokudaikon.infrastructure

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init(
        dbConfig: DbConfig,
        runMigrations: Boolean,
        drop: Boolean = false,
        test: Boolean = false,
    ) {
        val config = HikariConfig().apply {
            driverClassName = dbConfig.driver
            jdbcUrl = dbConfig.url
            this.username = dbConfig.username
            this.password = dbConfig.password
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            maximumPoolSize = 5
            idleTimeout = 10000
            connectionTimeout = 10000
            validate()
        }

        val ds = HikariDataSource(config)
        Database.connect(ds)

        val tables = emptyArray<Table>()

        if (drop) {
            transaction {
                tables.forEach { table ->
                    SchemaUtils.drop(table)
                }
                SchemaUtils.drop(Table("flyway_schema_history"))
            }
        }

        if (runMigrations) {
            val flyway = if (!test) {
                Flyway.configure()
                    .baselineOnMigrate(true)
                    .dataSource(ds)
                    .load()
            } else {
                null
            }

            // If database is empty, first create missing tables and columns, then baseline
            // Else apply migrations before renamed tables and columns are created as new instead of being renamed
            if (flyway?.info()?.current() != null) {
                flyway.migrate()
            }

            transaction {
                tables.forEach { table ->
                    SchemaUtils.createMissingTablesAndColumns(table)
                }
            }

            if (flyway?.info()?.current() == null) {
                flyway?.migrate()
            }
        }
    }
}
