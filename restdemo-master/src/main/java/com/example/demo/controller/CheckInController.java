//CheckInController.java
package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Checkin;
import com.example.demo.model.Registration;
import com.example.demo.model.User;
import com.example.demo.repository.CheckinRepository;
import com.example.demo.repository.RegistrationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CheckinService;

import java.util.List;

@RestController
public class CheckInController {

    @Autowired
    private CheckinService checkinService;
    
    @Autowired
    private CheckinRepository checkinRepository;
    
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private UserRepository userRepository;


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
    
    
 
    @GetMapping("/signIn")
    public ResponseEntity<List<Map<String, String>>> getCheckinsByEvent(@RequestParam("event_id") Integer eventId) {
        List<Registration> registrations = registrationRepository.findByEventId(eventId);

        Map<Integer, Registration> regMap = registrations.stream()
            .collect(Collectors.toMap(Registration::getRegistrationId, r -> r));

        List<Checkin> checkins = checkinRepository.findByRegistrationIdIn(
            registrations.stream().map(Registration::getRegistrationId).collect(Collectors.toList())
        );

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<Map<String, String>> response = new ArrayList<>();

        for (Checkin c : checkins) {
            Registration reg = regMap.get(c.getRegistrationId());
            if (reg != null) {
                Integer userId = reg.getUserId();
                Optional<User> userOpt = userRepository.findById(userId);

                if (userOpt.isPresent()) {
                    User user = userOpt.get();
                    Map<String, String> entry = new HashMap<>();
                    entry.put("student_id", userId.toString());
                    entry.put("name", user.getName());  // ✅ Use actual user name
                    entry.put("sign_in_time", c.getCheckinTime().format(formatter));
                    response.add(entry);
                }
            }
        }

        return ResponseEntity.ok(response);
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