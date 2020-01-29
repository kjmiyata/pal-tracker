package io.pivotal.pal.tracker

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import javax.sql.DataSource

@SpringBootApplication
open class PalTrackerApplication {

    @Bean
    open fun repo(dataSource: DataSource): TimeEntryRepository {
        return JdbcTimeEntryRepository(dataSource)
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(PalTrackerApplication::class.java, *args)
}