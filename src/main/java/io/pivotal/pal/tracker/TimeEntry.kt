package io.pivotal.pal.tracker

import java.time.LocalDate

data class TimeEntry(
        var id: Long = 1L,

        val projectId: Long,

        val userId: Long,

        val date: LocalDate,

        val hours: Int
) {
    constructor() : this(id = -1L, projectId = -1L, userId = -1L, date = LocalDate.EPOCH, hours = -1)
    constructor(projectId: Long, userId: Long, date: LocalDate, hours: Int) : this(-1L, projectId, userId, date, hours)

    override fun equals(other: Any?): Boolean {
        return if (other is TimeEntry) {
            other.date == this.date && other.hours == this.hours && other.projectId == this.projectId && other.userId == this.userId
        } else false
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + projectId.hashCode()
        result = 31 * result + userId.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + hours
        return result
    }
}