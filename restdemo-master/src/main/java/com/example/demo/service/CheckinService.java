//CheckinRepository.java
package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Checkin;
import com.example.demo.model.Registration;
import com.example.demo.repository.CheckinRepository;
import com.example.demo.repository.RegistrationRepository;

@Service
public class CheckinService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private CheckinRepository checkinRepository;

    public String checkInUser(int eventId, String qrToken) {
        // Find approved registration by event ID and token
        Registration registration = registrationRepository
            .findByEventIdAndQrTokenAndStatus(eventId, qrToken, Registration.STATUS_APPROVED)
            .orElseThrow(() -> new IllegalArgumentException("No approved registration found for this QR token and event."));

        int registrationId = registration.getRegistrationId();

        Optional<Checkin> optionalCheckin = checkinRepository.findByRegistrationId(registrationId);

        if (optionalCheckin.isPresent()) {
            Checkin checkin = optionalCheckin.get();
            checkin.setCheckinTime(LocalDateTime.now());
            checkinRepository.save(checkin);
            return "Check-in time updated (already checked in before).";
        } else {
            Checkin newCheckin = new Checkin(registrationId, LocalDateTime.now());
            checkinRepository.save(newCheckin);
            return "Check-in successful.";
        }
    }

}

