package io.pivotal.pal.tracker

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.ResultSetExtractor
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import java.sql.Connection
import java.sql.Date
import java.sql.ResultSet
import java.sql.Statement
import javax.sql.DataSource

class JdbcTimeEntryRepository(dataSource: DataSource) : TimeEntryRepository {

    private val jdbcTemplate: JdbcTemplate = JdbcTemplate(dataSource)

    override fun create(timeEntry: TimeEntry): TimeEntry {
        val generatedKeyHolder: KeyHolder = GeneratedKeyHolder()
        jdbcTemplate.update({ connection: Connection ->
            val statement = connection.prepareStatement(
                    "INSERT INTO time_entries (project_id, user_id, date, hours) " +
                            "VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            )
            statement.setLong(1, timeEntry.projectId)
            statement.setLong(2, timeEntry.userId)
            statement.setDate(3, Date.valueOf(timeEntry.date))
            statement.setInt(4, timeEntry.hours)
            statement
        }, generatedKeyHolder)
        return find(generatedKeyHolder.key!!.toLong())!!
    }

    override fun find(id: Long): TimeEntry? = jdbcTemplate.query(
            "SELECT id, project_id, user_id, date, hours FROM time_entries WHERE id = ?", arrayOf<Any>(id),
            extractor)

    override fun list(): List<TimeEntry> =
            jdbcTemplate.query("SELECT id, project_id, user_id, date, hours FROM time_entries", mapper)

    override fun update(id: Long, timeEntry: TimeEntry): TimeEntry? {
        jdbcTemplate.update("UPDATE time_entries " +
                "SET project_id = ?, user_id = ?, date = ?,  hours = ? " +
                "WHERE id = ?",
                timeEntry.projectId,
                timeEntry.userId,
                Date.valueOf(timeEntry.date),
                timeEntry.hours,
                id)
        return find(id)
    }

    override fun delete(id: Long) {
        jdbcTemplate.update("DELETE FROM time_entries WHERE id = ?", id)
    }

    private val mapper = RowMapper { rs: ResultSet, _: Int ->
        TimeEntry(
                rs.getLong("id"),
                rs.getLong("project_id"),
                rs.getLong("user_id"),
                rs.getDate("date").toLocalDate(),
                rs.getInt("hours")
        )
    }

    private val extractor = ResultSetExtractor { rs: ResultSet -> if (rs.next()) mapper.mapRow(rs, 1) else null }

}