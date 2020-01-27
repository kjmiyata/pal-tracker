package io.pivotal.pal.tracker

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.RestController

@RestController
class EnvController(
        @Value("\${cf.instance.index:NOT SET}")
        private val port: String,
        @Value("\${cf.instance.index:NOT SET}")
        private val memoryLimit: String,
        @Value("\${cf.instance.index:NOT SET}")
        private val instanceIndex: String,
        @Value("\${cf.instance.index:NOT SET}")
        private val instanceAddress: String
) {
    var env: Map<String, String> = mapOf(
            Pair("PORT", port),
            Pair("MEMORY_LIMIT", memoryLimit),
            Pair("CF_INSTANCE_INDEX", instanceIndex),
            Pair("CF_INSTANCE_ADDR", instanceAddress)
    )
}