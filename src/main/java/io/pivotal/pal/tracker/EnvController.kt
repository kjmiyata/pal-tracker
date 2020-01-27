package io.pivotal.pal.tracker

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class EnvController(
        @Value(value = "\${cf.instance.index:NOT SET}")
        private val port: String,

        @Value(value = "\${cf.instance.index:NOT SET}")
        private val memoryLimit: String,

        @Value(value = "\${cf.instance.index:NOT SET}")
        private val instanceIndex: String,

        @Value(value = "\${cf.instance.index:NOT SET}")
        private val instanceAddress: String
) {
    var env: Map<String, String> = mapOf(
            Pair("PORT", port),
            Pair("MEMORY_LIMIT", memoryLimit),
            Pair("CF_INSTANCE_INDEX", instanceIndex),
            Pair("CF_INSTANCE_ADDR", instanceAddress)
    )
        @GetMapping(value = ["/env"]) get
}