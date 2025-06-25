//CheckinRepository.java
package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Checkin;

import java.util.List;
@Repository
public interface CheckinRepository extends JpaRepository<Checkin, Integer> 
{
	@Query("SELECT c FROM Checkin c WHERE c.registrationId = :registrationId")
	Optional<Checkin> findByRegistrationId(@Param("registrationId") int registrationId);

	List<Checkin> findByRegistrationIdIn(List<Integer> registrationIds);

}
