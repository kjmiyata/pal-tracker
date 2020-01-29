package io.pivotal.pal.tracker

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource

@SpringBootApplication
open class PalTrackerApplication {

    open fun dataSource(): JdbcTemplate {
        return JdbcTemplate()
    }

    @Bean
    open fun repo(): TimeEntryRepository {
        return InMemoryTimeEntryRepository()
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(PalTrackerApplication::class.java, *args)
}