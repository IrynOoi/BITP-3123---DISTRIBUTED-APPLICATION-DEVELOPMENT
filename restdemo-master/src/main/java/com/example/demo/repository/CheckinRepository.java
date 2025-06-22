//CheckinRepository.java
package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Checkin;

@Repository
public interface CheckinRepository extends JpaRepository<Checkin, Integer> {
    Optional<Checkin> findByRegistrationId(int registrationId);
}

