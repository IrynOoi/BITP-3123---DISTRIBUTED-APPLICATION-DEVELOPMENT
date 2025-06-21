// CheckInController.java
package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.CheckinService;

@RestController
public class CheckInController {

    @Autowired
    private CheckinService checkinService;

    @GetMapping("/checkin")
    public ResponseEntity<String> checkIn(@RequestParam String userId, @RequestParam String eventId) {
        try {
            checkinService.markUserCheckedIn(userId, eventId);
            return ResponseEntity.ok("User " + userId + " has checked in for event " + eventId);
        } catch (DataAccessException e) {
            // Unwrap root cause from Spring's DataAccessException
            Throwable rootCause = e.getMostSpecificCause();
            
            if (rootCause instanceof IllegalArgumentException 
                || rootCause instanceof IllegalStateException) {
                // Business rule violation - return 400
                return ResponseEntity.badRequest().body(rootCause.getMessage());
            } else {
                // Genuine database error - log and return 500
                e.printStackTrace();
                return ResponseEntity.status(500).body("Database error");
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            // Handle direct exceptions from service layer
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // Other unexpected errors
            e.printStackTrace();
            return ResponseEntity.status(500).body("Internal server error");
        }
    }
    
    
//    @PostMapping("/checkin")
//    public ResponseEntity<String> checkIn(@RequestBody Map<String, String> payload) {
//        String userId = payload.get("userId");
//        String eventId = payload.get("eventId");
//
//        if (userId == null || eventId == null) {
//            return ResponseEntity.badRequest().body("Missing userId or eventId");
//        }
//
//        try {
//            checkinService.markUserCheckedIn(userId, eventId);
//            return ResponseEntity.ok("User " + userId + " checked in successfully for event " + eventId);
//        } catch (DataAccessException e) {
//            Throwable rootCause = e.getMostSpecificCause();
//            if (rootCause instanceof IllegalArgumentException || rootCause instanceof IllegalStateException) {
//                return ResponseEntity.badRequest().body(rootCause.getMessage());
//            }
//            e.printStackTrace();
//            return ResponseEntity.status(500).body("Database error");
//        } catch (IllegalArgumentException | IllegalStateException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(500).body("Internal server error");
//        }
//    }

    @GetMapping("/ping")
    public String ping() {
        return "OK";
    }
}