//EventRepository.java
package com.example.demo.repository;

import com.example.demo.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class EventRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EventRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Event> findByQrcodeToken(String qrToken) {
        String sql = "SELECT * FROM event WHERE qrcode_token = ?";
        
        try {
            Event event = jdbcTemplate.queryForObject(sql, new EventRowMapper(), qrToken);
            return Optional.ofNullable(event);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private static class EventRowMapper implements RowMapper<Event> {
        @Override
        public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
            Event event = new Event();
            event.setEventId(rs.getInt("event_id"));
            event.setTitle(rs.getString("title"));
            event.setDescription(rs.getString("description"));
            event.setEndDateTime(rs.getTimestamp("start_datetime").toLocalDateTime());
            event.setEndDateTime(rs.getTimestamp("end_datetime").toLocalDateTime());
            event.setLocation(rs.getString("location"));
            event.setCapacity(rs.getInt("capacity"));
            event.setUserId(rs.getInt("user_id"));
            event.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            event.setQrcodeToken(rs.getString("qrcode_token"));
            event.setFirestorePath(rs.getString("firestore_path"));
            return event;
        }
    }
}