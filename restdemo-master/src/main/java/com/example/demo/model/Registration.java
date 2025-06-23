//Registration.java
package com.example.demo.model;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name = "registration")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_id", nullable = false, updatable = false)
    private Integer registrationId;

    @Column(name = "event_id", nullable = false)
    private Integer eventId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    // Use String for status instead of enum
    @Column(name = "status", nullable = false, length = 20)
    private String status = STATUS_PENDING;

    // New field from SQL: qr_token varchar(32) NOT NULL
    @Column(name = "qr_token", length = 32, nullable = false)
    private String qrToken;

    @Column(name = "registration_time", nullable = false)
    private LocalDateTime registrationTime;

    // String constants for status values
    public static final String STATUS_PENDING = "pending";
    public static final String STATUS_APPROVED = "approved";
    public static final String STATUS_REJECTED = "rejected";

    // Constructors
    public Registration() {}

    public Registration(Integer eventId, Integer userId, String status, String qrToken, LocalDateTime registrationTime) {
        this.eventId = eventId;
        this.userId = userId;
        this.status = status;
        this.qrToken = qrToken;
        this.registrationTime = registrationTime;
    }

    // Getters and setters
    public Integer getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQrToken() {
        return qrToken;
    }

    public void setQrToken(String qrToken) {
        this.qrToken = qrToken;
    }

    public LocalDateTime getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(LocalDateTime registrationTime) {
        this.registrationTime = registrationTime;
    }
}
