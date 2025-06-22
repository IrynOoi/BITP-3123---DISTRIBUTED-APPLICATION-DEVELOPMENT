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

    public Integer checkinUserInMySQL(int userId, int eventId)
    {
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
    }
     
    // New method for QR-based check-in
    public Integer checkinByQRToken(int userId, String qrToken) {
        // 1. Get event ID from QR token
        String eventQuery = "SELECT event_id FROM event WHERE qrcode_token = ?";
        Integer eventId;
        
        try {
            eventId = jdbcTemplate.queryForObject(eventQuery, Integer.class, qrToken);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("Invalid QR code");
        }
        
        // 2. Check if registration exists and is approved
        String regQuery = "SELECT registration_id FROM registration " +
                          "WHERE user_id = ? AND event_id = ? AND status = 'approved'";
        Integer registrationId;
        
        try {
            registrationId = jdbcTemplate.queryForObject(regQuery, Integer.class, userId, eventId);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalStateException("No approved registration found");
        }
        
        // 3. Check for existing check-in
        String checkExisting = "SELECT COUNT(*) FROM checkin WHERE registration_id = ?";
        Integer count = jdbcTemplate.queryForObject(checkExisting, Integer.class, registrationId);
        
        if (count != null && count > 0) {
            throw new IllegalStateException("Already checked in");
        }
        
        // 4. Perform check-in
        jdbcTemplate.update(
            "INSERT INTO checkin (registration_id, checkin_time) VALUES (?, ?)",
            registrationId, 
            Timestamp.valueOf(LocalDateTime.now())
        );
        
        return registrationId;
    }
    
    
    
    }
