// RegistrationRepository.java
package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

    // ✅ Find all registrations for an event
    List<Registration> findByEventId(Integer eventId);

    // ✅ Find a single registration by event + user (used in approveRegistration)
    Optional<Registration> findByEventIdAndUserId(Integer eventId, Integer userId);

    // ✅ Optional QR token validation
    @Query("SELECT r FROM Registration r WHERE r.eventId = :eventId AND r.qrToken = :qrToken AND r.status = :status")
    Optional<Registration> findByEventIdAndQrTokenAndStatus(@Param("eventId") int eventId,
                                                            @Param("qrToken") String qrToken,
                                                            @Param("status") String status);
}
