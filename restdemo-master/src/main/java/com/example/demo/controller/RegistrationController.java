//RegistrationController.java

package com.example.demo.controller;

import com.example.demo.model.Registration;
import com.example.demo.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private RegistrationRepository registrationRepository;

    // ✅ GET /api/registrations?event_id=123
    @GetMapping("/registrations")
    public ResponseEntity<List<Map<String, String>>> getRegistrationsByEventId(@RequestParam("event_id") Integer eventId) {
        List<Registration> registrations = registrationRepository.findAll().stream()
                .filter(r -> r.getEventId().equals(eventId))
                .collect(Collectors.toList());

        List<Map<String, String>> response = new ArrayList<>();
        for (Registration r : registrations) {
            Map<String, String> entry = new HashMap<>();
            entry.put("student_id", r.getUserId().toString());
            entry.put("name", "Student " + r.getUserId()); // Replace with real name if possible
            entry.put("status", r.getStatus());
            response.add(entry);
        }

        return ResponseEntity.ok(response);
    }

 // ✅ POST /api/updateRegistrationStatus
    @PostMapping("/updateRegistrationStatus")
    public ResponseEntity<String> updateRegistrationStatus(@RequestBody Map<String, String> payload) {
        try {
            Integer eventId = Integer.parseInt(payload.get("event_id"));
            Integer studentId = Integer.parseInt(payload.get("student_id"));
            String status = payload.get("status");

            Optional<Registration> regOpt = registrationRepository.findByEventIdAndUserId(eventId, studentId);
            if (!regOpt.isPresent()) {
                return ResponseEntity.status(404).body("Registration not found.");
            }

            Registration registration = regOpt.get();
            registration.setStatus(status);
            registrationRepository.save(registration);

            return ResponseEntity.ok("Status updated to " + status);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Invalid request: " + e.getMessage());
        }
    }

}
