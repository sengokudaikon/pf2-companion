package io.sengokudaikon.isn.infrastructure.repository

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.BooleanColumnType
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ColumnType
import org.jetbrains.exposed.sql.ComparisonOp
import org.jetbrains.exposed.sql.DoubleColumnType
import org.jetbrains.exposed.sql.Expression
import org.jetbrains.exposed.sql.FloatColumnType
import org.jetbrains.exposed.sql.Function
import org.jetbrains.exposed.sql.IntegerColumnType
import org.jetbrains.exposed.sql.LongColumnType
import org.jetbrains.exposed.sql.QueryBuilder
import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.TextColumnType
import org.jetbrains.exposed.sql.UUIDColumnType
import org.jetbrains.exposed.sql.statements.api.PreparedStatementApi
import org.postgresql.util.PGobject
import java.util.*

private const val JSONB = "JSONB"

/**
 * @see <a href="https://github.com/JetBrains/Exposed/issues/127#issuecomment-624222176">GitHub</a>
 */
class JsonbColumnType<T : Any>(private val serializer: KSerializer<T>) : ColumnType() {
    override fun sqlType() = JSONB

    override fun setParameter(stmt: PreparedStatementApi, index: Int, value: Any?) {
        super.setParameter(
            stmt,
            index,
            value.let {
                PGobject().apply {
                    this.type = sqlType()
                    this.value = value as? String
                }
            },
        )
    }

    override fun valueFromDB(value: Any): Any {
        val stringValue = when (value) {
            is PGobject -> value.value!!
            else -> value.toString()
        }
        return Json.decodeFromString(serializer, stringValue)
    }

    override fun valueToString(value: Any?): String = when (value) {
        is Iterable<*> -> nonNullValueToString(value)
        else -> super.valueToString(value)
    }

    @Suppress("UNCHECKED_CAST")
    override fun notNullValueToDB(value: Any): Any {
        return Json.encodeToString(serializer, value as T)
    }
}

fun <T : Any> Table.jsonb(name: String, serializer: KSerializer<T>): Column<String> =
    registerColumn(name, JsonbColumnType(serializer))

class JsonValue<T>(
    val expr: Expression<*>,
    override val columnType: ColumnType,
    val jsonPath: List<String>,
) : Function<T>(columnType) {
    override fun toQueryBuilder(queryBuilder: QueryBuilder) = queryBuilder {
        val castJson = columnType.sqlType() != JSONB
        if (castJson) append("(")
        append(expr)
        append(" #>")
        if (castJson) append(">")
        append(" '{${jsonPath.joinToString { escapeFieldName(it) }}}'")
        if (castJson) append(")::${columnType.sqlType()}")
    }

    companion object {

        private fun escapeFieldName(value: String) = value.map {
            fieldNameCharactersToEscape[it] ?: it
        }.joinToString("").let { "\"$it\"" }

        private val fieldNameCharactersToEscape = mapOf(
            // '\"' to "\'\'", // no need to escape single quote as we put string in double quotes
            '\"' to "\\\"",
            '\r' to "\\r",
            '\n' to "\\n",
        )
    }
}

inline fun <reified T : Any> Column<*>.json(serializer: KSerializer<T>, vararg jsonPath: String): JsonValue<T> {
    val columnType = when (T::class) {
        Boolean::class -> BooleanColumnType()
        Int::class -> IntegerColumnType()
        Long::class -> LongColumnType()
        Double::class -> DoubleColumnType()
        Float::class -> FloatColumnType()
        String::class -> TextColumnType()
        UUID::class -> UUIDColumnType()
        else -> JsonbColumnType(serializer)
    }

    return JsonValue(this, columnType, jsonPath.toList())
}

class JsonContainsOp(expr1: Expression<*>, expr2: Expression<*>) : ComparisonOp(expr1, expr2, "??")

/** Checks if this expression contains some [t] value. */
infix fun <T> JsonValue<Any>.contains(t: T): JsonContainsOp =
    JsonContainsOp(this, SqlExpressionBuilder.run { this@contains.wrap(t) })

/** Checks if this expression contains some [other] expression. */
infix fun <T> JsonValue<Any>.contains(other: Expression<T>): JsonContainsOp =
    JsonContainsOp(this, other)
