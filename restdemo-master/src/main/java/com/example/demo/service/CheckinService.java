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

    public String checkInUser(int userId, int eventId) {
        Registration registration = registrationRepository
            .findByUserIdAndEventIdAndStatus(userId, eventId, Registration.STATUS_APPROVED)
            .orElseThrow(() -> new IllegalArgumentException("Registration not found or not approved for user " + userId + " and event " + eventId));

        int registrationId = registration.getRegistrationId();

        Optional<Checkin> optionalCheckin = checkinRepository.findByRegistrationId(registrationId);

        if (optionalCheckin.isPresent()) {
            Checkin checkin = optionalCheckin.get();
            checkin.setCheckinTime(LocalDateTime.now());
            checkinRepository.save(checkin);
            return "Check-in time updated (you have already checked in)";
        } else {
            Checkin newCheckin = new Checkin(registrationId, LocalDateTime.now());
            checkinRepository.save(newCheckin);
            return "Checked in successfully";
        }
    }

}
