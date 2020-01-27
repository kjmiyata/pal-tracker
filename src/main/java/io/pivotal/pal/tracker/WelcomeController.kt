package io.pivotal.pal.tracker

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
open class WelcomeController {

    @GetMapping("/")
    fun sayHello(): String = "hello"
}