//RegistrationService
package com.example.demo.service;

import com.example.demo.model.Registration;
import com.example.demo.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    // ✅ Get all registrations for a given event ID
    public List<Registration> getRegistrationsByEventId(Integer eventId) {
        return registrationRepository.findByEventId(eventId);
    }

    // ✅ Approve a registration for a given event ID and user ID
    @Transactional
    public void approveRegistration(Integer eventId, Integer userId) {
        Registration registration = registrationRepository.findByEventIdAndUserId(eventId, userId)
            .orElseThrow(() -> new IllegalArgumentException("Registration not found for event " + eventId + " and user " + userId));

        registration.setStatus(Registration.STATUS_APPROVED);
        registrationRepository.save(registration);
    }

    // Optional: Add new registration
    public void addRegistration(Registration registration) {
        registrationRepository.save(registration);
    }
}

