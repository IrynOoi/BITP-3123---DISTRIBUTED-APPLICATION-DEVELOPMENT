//EventController
package com.example.demo.controller;
import com.example.demo.model.AttendanceStats;
import com.example.demo.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.AttendanceStatsService;
import com.example.demo.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventController
{

    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    public List<Event> getEvents() {
        return eventService.getAllEvents();
    }

    @Autowired
    private AttendanceStatsService statsService;

    @GetMapping("/attendanceStats")
    public AttendanceStats getStats(@RequestParam String event_id) {
        return statsService.getStatsByEventId(event_id);
    }
}