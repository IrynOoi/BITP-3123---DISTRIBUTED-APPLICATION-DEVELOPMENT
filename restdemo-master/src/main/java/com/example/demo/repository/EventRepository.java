package com.example.demo.repository;

import com.example.demo.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    // Get events by user (admin) ID
    @Query(value = "SELECT * FROM event WHERE user_id = :userId", nativeQuery = true)
    List<Event> findEventsByUserId(@Param("userId") int userId);

    // Find event by QR token
    @Query(value = "SELECT * FROM event WHERE qrcode_token = :token", nativeQuery = true)
    Event findByQrCodeToken(@Param("token") String token);

    // Get events at a specific location
    @Query(value = "SELECT * FROM event WHERE location = :location", nativeQuery = true)
    List<Event> findEventsByLocation(@Param("location") String location);

    // Get future events
    @Query(value = "SELECT * FROM event WHERE start_datetime > NOW()", nativeQuery = true)
    List<Event> findUpcomingEvents();
}
