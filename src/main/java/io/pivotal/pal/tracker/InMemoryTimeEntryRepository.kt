package io.pivotal.pal.tracker

open class InMemoryTimeEntryRepository : TimeEntryRepository {

    var counter = 1L

    var entries: MutableMap<Long, TimeEntry> = hashMapOf()

    override fun create(timeEntry: TimeEntry): TimeEntry {
        if (timeEntry.id < 0) {
            timeEntry.id = counter
            counter++
        }

        entries[timeEntry.id] = timeEntry
        return entries[timeEntry.id]!!
    }

    override fun find(id: Long): TimeEntry? {
        return entries[id]
    }

    override fun update(id: Long, timeEntry: TimeEntry): TimeEntry? {
        if (timeEntry.id < 0) {
            timeEntry.id = id
        }

        return if (entries[id] != null) {
            entries[id] = timeEntry
            entries[id]
        } else {
            null
        }
    }

    override fun list(): List<TimeEntry> {
        val listOfThing = arrayListOf<TimeEntry>()

        entries.forEach { (_, entry) ->
            listOfThing.add(entry)
        }

        return listOfThing
    }

    override fun delete(id: Long) {
        entries.remove(id)
    }
}