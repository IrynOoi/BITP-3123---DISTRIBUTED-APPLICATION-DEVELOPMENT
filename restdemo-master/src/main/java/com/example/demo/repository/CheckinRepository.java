//CheckinRepository.java
package com.example.demo.repository;

import java.time.LocalDateTime;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CheckinRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Integer checkinUserInMySQL(int userId, int eventId) {
        String query = "SELECT registration_id FROM registration WHERE user_id = ? AND event_id = ? AND status = 'approved' LIMIT 1";
        Integer registrationId;

        try {
            registrationId = jdbcTemplate.queryForObject(query, Integer.class, userId, eventId);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("No approved registration found for userId=" + userId + " and eventId=" + eventId);
        }

        String checkExisting = "SELECT COUNT(*) FROM checkin WHERE registration_id = ?";
        Integer count = jdbcTemplate.queryForObject(checkExisting, Integer.class, registrationId);
        if (count != null && count > 0) {
            throw new IllegalStateException("User already checked in.");
        }

        jdbcTemplate.update("INSERT INTO checkin (registration_id, checkin_time) VALUES (?, ?)",
                registrationId, Timestamp.valueOf(LocalDateTime.now()));

        return registrationId;
    }}
