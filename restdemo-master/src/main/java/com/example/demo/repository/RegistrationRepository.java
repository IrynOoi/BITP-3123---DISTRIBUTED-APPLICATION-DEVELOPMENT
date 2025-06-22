package com.example.demo.repository;
import com.example.demo.model.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class RegistrationRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RegistrationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Registration> findByUserIdAndEventIdAndStatus(int userId, int eventId, String status) {
        String sql = "SELECT * FROM registration WHERE user_id = ? AND event_id = ? AND status = ?";
        
        try {
            Registration registration = jdbcTemplate.queryForObject(
                sql, 
                new RegistrationRowMapper(), 
                userId, eventId, status
            );
            return Optional.ofNullable(registration);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private static class RegistrationRowMapper implements RowMapper<Registration> {
        @Override
        public Registration mapRow(ResultSet rs, int rowNum) throws SQLException {
            Registration registration = new Registration();
            registration.setRegistrationId(rs.getInt("registration_id"));
            registration.setEventId(rs.getInt("event_id"));
            registration.setUserId(rs.getInt("user_id"));
            registration.setStatus(rs.getString("status"));
            registration.setRegistrationTime(rs.getTimestamp("registration_time").toLocalDateTime());
            return registration;
        }
    }
}