package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "checkin")
public class Checkin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checkin_id")
    private Long checkinId;

    @Column(name = "registration_id", nullable = false, unique = true)
    private Long registrationId;

    @Column(name = "checkin_time", nullable = false)
    private LocalDateTime checkinTime;

    // Constructors
    public Checkin() {
    }

    public Checkin(Long registrationId, LocalDateTime checkinTime) {
        this.registrationId = registrationId;
        this.checkinTime = checkinTime;
    }

    // Getters and Setters
    public Long getCheckinId() {
        return checkinId;
    }

    public void setCheckinId(Long checkinId) {
        this.checkinId = checkinId;
    }

    public Long getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Long registrationId) {
        this.registrationId = registrationId;
    }

    public LocalDateTime getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(LocalDateTime checkinTime) {
        this.checkinTime = checkinTime;
    }
}
