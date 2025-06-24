//CheckinService.java
package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Checkin;
import com.example.demo.model.Registration;
import com.example.demo.repository.CheckinRepository;
import com.example.demo.repository.RegistrationRepository;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class CheckinService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private CheckinRepository checkinRepository;

    public String checkInUser(int eventId, String qrToken) {
        Registration registration = registrationRepository
            .findByEventIdAndQrTokenAndStatus(eventId, qrToken, Registration.STATUS_APPROVED)
            .orElseThrow(() -> new IllegalArgumentException("No approved registration found for this QR token and event."));

        int registrationId = registration.getRegistrationId();
        Optional<Checkin> optionalCheckin = checkinRepository.findByRegistrationId(registrationId);
        LocalDateTime now = LocalDateTime.now();

        if (optionalCheckin.isPresent()) {
            Checkin checkin = optionalCheckin.get();
            checkin.setCheckinTime(now);
            checkinRepository.save(checkin);
            syncCheckinToFirebase(registrationId, eventId, qrToken, now);
            return "Check-in time updated (already checked in before).";
        } else {
            Checkin newCheckin = new Checkin(registrationId, now);
            checkinRepository.save(newCheckin);
            syncCheckinToFirebase(registrationId, eventId, qrToken, now);
            return "Check-in successful.";
        }
    }

    private void syncCheckinToFirebase(int registrationId, int eventId, String qrToken, LocalDateTime checkinTime) {
        try {
            Firestore db = FirestoreClient.getFirestore();
            Map<String, Object> data = new HashMap<>();
            data.put("registrationId", registrationId);
            data.put("eventId", eventId);
            data.put("qrToken", qrToken);
            data.put("checkinTime", checkinTime.toString());

            db.collection("checkins").document(String.valueOf(registrationId)).set(data);
        } catch (Exception e) {
            // Log but don't crash the service on Firebase failure
            System.err.println("Error syncing check-in to Firebase: " + e.getMessage());
        }
    }

}

