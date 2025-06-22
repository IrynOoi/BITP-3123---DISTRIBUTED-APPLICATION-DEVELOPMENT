//Registration.java
package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
    Optional<Registration> findByUserIdAndEventIdAndStatus(int userId, int eventId, String status);
}
