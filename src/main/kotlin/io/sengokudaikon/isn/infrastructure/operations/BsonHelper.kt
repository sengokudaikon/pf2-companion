@file:Suppress("TooManyFunctions")

package io.sengokudaikon.isn.infrastructure.operations

import io.sengokudaikon.isn.infrastructure.logger
import org.bson.BsonType
import org.bson.BsonValue

@Suppress("CyclomaticComplexMethod")
fun BsonValue.transform(): String? {
    return when (this.bsonType) {
        BsonType.DOUBLE -> transformDouble()
        BsonType.STRING -> transformString()
        BsonType.DOCUMENT -> transformDocument()
        BsonType.ARRAY -> transformArray()
        BsonType.BINARY -> transformBinary()
        BsonType.UNDEFINED -> transformUndefined().toString()
        BsonType.OBJECT_ID -> transformObjectId()
        BsonType.INT32 -> transformInt32()
        BsonType.INT64 -> transformInt64()
        BsonType.JAVASCRIPT -> transformJavaScript()
        BsonType.JAVASCRIPT_WITH_SCOPE -> transformJavaScriptWithScope()
        BsonType.BOOLEAN -> transformBoolean()
        BsonType.NULL -> transformNull().toString()
        BsonType.REGULAR_EXPRESSION -> transformRegularExpression()
        BsonType.DB_POINTER -> transformDbPointer()
        BsonType.SYMBOL -> transformSymbol()
        BsonType.DECIMAL128 -> transformDecimal128()
        BsonType.MIN_KEY -> transformMinKey().toString()
        else -> transformDefault().toString()
    }
}

private fun BsonValue.transformDouble() = this.asDouble().value.toFloat().toString()
private fun BsonValue.transformString() = this.asString().value.toString()
private fun BsonValue.transformDocument() = this.asDocument().toJson()
private fun BsonValue.transformArray() = this.asArray().map {
    it.transform()
}.joinToString(", ")

private fun BsonValue.transformBinary() = this.asBinary().data.toString()
private fun transformUndefined(): Unit? {
    logger.warn("Undefined value found")
    return null
}

private fun BsonValue.transformObjectId() = this.asObjectId().value.toString()
private fun BsonValue.transformInt32() = this.asInt32().value.toString()
private fun BsonValue.transformInt64() = this.asInt64().value.toString()
private fun BsonValue.transformJavaScript() = this.asJavaScript().code
private fun BsonValue.transformJavaScriptWithScope() = this.asJavaScriptWithScope().code
private fun BsonValue.transformBoolean() = this.asBoolean().value.toString()
private fun transformNull(): Unit? {
    logger.warn("Null value found")
    return null
}

private fun BsonValue.transformRegularExpression() = this.asRegularExpression().pattern
private fun BsonValue.transformDbPointer() = this.asDBPointer().id.toString()
private fun BsonValue.transformSymbol() = this.asSymbol().symbol
private fun BsonValue.transformDecimal128() = this.asDecimal128().value.toString()

@Suppress("FunctionOnlyReturningConstant")
private fun transformMinKey() = null
private fun BsonValue.transformDefault(): Unit? {
    logger.warn("Unknown BsonType: ${this.bsonType}")
    return null
}
