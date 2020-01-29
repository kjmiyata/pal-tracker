package io.pivotal.pal.tracker

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WelcomeController(@Value(value = "\${welcome.message}") private var message: String) {

    @GetMapping(path = ["/"])
    fun sayHello(): String = message
}