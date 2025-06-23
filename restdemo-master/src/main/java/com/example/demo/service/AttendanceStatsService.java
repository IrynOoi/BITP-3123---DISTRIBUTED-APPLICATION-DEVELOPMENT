//AttendanceStatsService.java
package com.example.demo.service;

import com.example.demo.model.AttendanceStats;
import com.example.demo.repository.AttendanceStatsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceStatsService {

    private final AttendanceStatsRepository attendanceStatsRepository;

    public AttendanceStatsService(AttendanceStatsRepository attendanceStatsRepository) {
        this.attendanceStatsRepository = attendanceStatsRepository;
    }

    public List<AttendanceStats> getAllAttendanceStats() {
        return attendanceStatsRepository.findAll();
    }

    // ✅ Add this method to resolve controller error
    public AttendanceStats getStatsByEventId(Integer eventId) 
    {
        return attendanceStatsRepository.findByEventId(eventId);
    }
}
