package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "registration")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_id")
    private int registrationId;

    @Column(name = "event_id", nullable = false)
    private int eventId;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "status", nullable = false)
    private String status = "pending";  // Default value

    @Column(name = "registration_time", nullable = false)
    private LocalDateTime registrationTime;

    // Optional: constants for allowed status values
    public static final String STATUS_PENDING = "pending";
    public static final String STATUS_APPROVED = "approved";
    public static final String STATUS_REJECTED = "rejected";

    // Constructors
    public Registration() {
    }

    public Registration(int eventId, int userId, String status, LocalDateTime registrationTime) {
        this.eventId = eventId;
        this.userId = userId;
        this.status = status;
        this.registrationTime = registrationTime;
    }

    // Getters and Setters
    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(int i) {
        this.registrationId = i;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int i) {
        this.eventId = i;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int i) {
        this.userId = i;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        // Optionally add validation:
        // if (!status.equals(STATUS_PENDING) && !status.equals(STATUS_APPROVED) && !status.equals(STATUS_REJECTED)) {
        //     throw new IllegalArgumentException("Invalid status: " + status);
        // }
        this.status = status;
    }

    public LocalDateTime getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(LocalDateTime registrationTime) {
        this.registrationTime = registrationTime;
    }
}
