//AttendanceStasController.java
package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.model.Event; // Ensure Event class has event_id and title
import com.example.demo.service.AttendanceStatsService;
import com.example.demo.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AttendanceStatsController {

    private final AttendanceStatsService attendanceStatsService;
    private final EventService eventService;

    public AttendanceStatsController(AttendanceStatsService attendanceStatsService, EventService eventService) {
        this.attendanceStatsService = attendanceStatsService;
        this.eventService = eventService;
    }

    @GetMapping("/attendance-events")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }
}


