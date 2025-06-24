//CheckInController.java
package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Checkin;
import com.example.demo.model.Registration;
import com.example.demo.repository.CheckinRepository;
import com.example.demo.repository.RegistrationRepository;
import com.example.demo.service.CheckinService;

@RestController
public class CheckInController {

    @Autowired
    private CheckinService checkinService;

    @GetMapping("/checkin")
    public ResponseEntity<String> checkInWithToken(@RequestParam int eventId, @RequestParam String qrToken) {
        try {
            String result = checkinService.checkInUser(eventId, qrToken);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Internal server error");
        }
    }



//    @PostMapping("/checkin/qr")
//    public ResponseEntity<String> checkInByQR(@RequestBody Map<String, String> payload) {
//        String userIdStr = payload.get("userId");
//        String qrToken = payload.get("qrToken");
//
//        if (userIdStr == null || qrToken == null) {
//            return ResponseEntity.badRequest().body("Missing userId or QR token");
//        }
//
//        int userId;
//        try {
//            userId = Integer.parseInt(userIdStr);
//        } catch (NumberFormatException e) {
//            return ResponseEntity.badRequest().body("Invalid userId format");
//        }
//
//        try {
//            checkinService.processQRCheckIn(userId, qrToken);
//            return ResponseEntity.ok("Checked in successfully via QR");
//        } catch (DataAccessException e) {
//            Throwable rootCause = e.getMostSpecificCause();
//            if (rootCause instanceof IllegalArgumentException || rootCause instanceof IllegalStateException) {
//                return ResponseEntity.badRequest().body(rootCause.getMessage());
//            } else {
//                e.printStackTrace();
//                return ResponseEntity.status(500).body("Database error");
//            }
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