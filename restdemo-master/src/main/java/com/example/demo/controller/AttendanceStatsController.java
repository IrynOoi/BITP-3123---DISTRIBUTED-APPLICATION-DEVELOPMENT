//AttendanceStatsController.java
package com.example.demo.controller;

import com.example.demo.model.AttendanceStats;
import com.example.demo.service.AttendanceStatsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//return all events attendance report for bar cbart generation(frontend)
@RestController
@RequestMapping("/api/attendance")
public class AttendanceStatsController 
{

    private final AttendanceStatsService attendanceStatsService;

    public AttendanceStatsController(AttendanceStatsService attendanceStatsService) {
        this.attendanceStatsService = attendanceStatsService;
    }

    @GetMapping("/report")
    public List<AttendanceStats> getAttendanceReport() 
    {
        return attendanceStatsService.getAllAttendanceStats();
    }
    
    
    
}
