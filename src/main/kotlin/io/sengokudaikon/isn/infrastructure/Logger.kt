package io.sengokudaikon.isn.infrastructure

import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.encoder.EncoderBase
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import java.io.IOException
import java.io.OutputStream

val logger: Logger = LoggerFactory.getLogger("GlobalLogger")
val exceptionLogger: Logger = LoggerFactory.getLogger("ExceptionLogger")

@Suppress("unused")
class LogFmtEncoder : EncoderBase<ILoggingEvent>() {
    private var outputStream: OutputStream? = null

    @Throws(IOException::class)
    override fun encode(event: ILoggingEvent): ByteArray {
        val stackTrace = if (event.throwableProxy != null) {
            event.throwableProxy.stackTraceElementProxyArray.joinToString("->") { it.toString() }
        } else {
            "none"
        }

        val mdcData = MDC.getCopyOfContextMap().orEmpty()
            .map { (key, value) -> "$key=$value" }.joinToString("\\n")

        val message = "{level=${event.level}, logger=${event.loggerName}, message=${
            event.formattedMessage.replace(
                "\n",
                "\\n",
            )
        }, thread=${event.threadName}, mdc=$mdcData, stacktrace=$stackTrace\n}"
        return message.toByteArray()
    }

    override fun headerBytes(): ByteArray {
        return byteArrayOf()
    }

    override fun footerBytes(): ByteArray {
        return byteArrayOf()
    }
}
