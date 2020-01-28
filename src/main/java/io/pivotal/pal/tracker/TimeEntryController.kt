package io.pivotal.pal.tracker

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class TimeEntryController(@Autowired var repository: TimeEntryRepository) {

    @PostMapping(value = ["/time-entries"])
    fun create(@RequestBody entry: TimeEntry): ResponseEntity<*> {
        return try {
            ResponseEntity.status(HttpStatus.CREATED).body(repository.create(entry))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build<Nothing>()
        }
    }

    @GetMapping(value = ["/time-entries/{timeEntryId}"])
    fun read(@PathVariable timeEntryId: Long): ResponseEntity<TimeEntry> {
        val thing = repository.find(timeEntryId)

        return if (thing != null) {
            ResponseEntity.ok(thing)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping(value = ["/time-entries"])
    fun list(): ResponseEntity<List<TimeEntry>> {
        return ResponseEntity.ok(repository.list())
    }

    @PutMapping(value = ["/time-entries/{timeEntryId}"])
    fun update(@PathVariable timeEntryId: Long, @RequestBody expected: TimeEntry): ResponseEntity<TimeEntry> {
        val thing = repository.update(timeEntryId, expected)

        return if (thing != null) {
            ResponseEntity.ok(thing)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping(value = ["/time-entries/{timeEntryId}"])
    fun delete(@PathVariable timeEntryId: Long): ResponseEntity<*> {
        repository.delete(timeEntryId)
        return ResponseEntity.noContent().build<Nothing>()
    }

}