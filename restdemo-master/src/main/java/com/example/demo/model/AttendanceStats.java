// AttendanceStats.java
package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
// This maps to the SQL view, not a real table
@Table(name = "attendance_stats")
public class AttendanceStats {

    @Id
    @Column(name = "event_id")
    private Integer eventId;

    private String title;

    @Column(name = "total_registrations")
    private int totalRegistrations;

    private int attended;

    @Column(name = "attendance_rate")
    private double attendanceRate;

    // === Getters and Setters ===

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalRegistrations() {
        return totalRegistrations;
    }

    public void setTotalRegistrations(int totalRegistrations) {
        this.totalRegistrations = totalRegistrations;
    }

    public int getAttended() {
        return attended;
    }

    public void setAttended(int attended) {
        this.attended = attended;
    }

    public double getAttendanceRate() {
        return attendanceRate;
    }

    public void setAttendanceRate(double attendanceRate) {
        this.attendanceRate = attendanceRate;
    }
}
