// RegistrationRepository.java
package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer>
{
	
//	@Query("SELECT r FROM Registration r WHERE r.userId = :userId AND r.eventId = :eventId AND r.status = :status")
//	Optional<Registration> findByUserIdAndEventIdAndStatus(@Param("userId") int userId,
//	                                                       @Param("eventId") int eventId,
//	                                                       @Param("status") String status);
	
	  // 🔁 New query: Find by event ID and QR token (no userId check)
    @Query("SELECT r FROM Registration r WHERE r.eventId = :eventId AND r.qrToken = :qrToken AND r.status = :status")
    Optional<Registration> findByEventIdAndQrTokenAndStatus(@Param("eventId") int eventId,
                                                            @Param("qrToken") String qrToken,
                                                            @Param("status") String status);

}
