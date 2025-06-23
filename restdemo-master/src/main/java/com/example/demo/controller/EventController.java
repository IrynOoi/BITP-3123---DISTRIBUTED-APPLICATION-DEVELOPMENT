//EventController
package com.example.demo.controller;
import com.example.demo.model.AttendanceStats;
import com.example.demo.model.Event;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.AttendanceStatsService;
import com.example.demo.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventController {

    private final EventService eventService;
    private final AttendanceStatsService statsService;

    public EventController(EventService eventService, AttendanceStatsService statsService) {
        this.eventService = eventService;
        this.statsService = statsService;
    }

    @GetMapping("/events")
    public List<Event> getEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/attendanceStats")
    public AttendanceStats getStats(@RequestParam String event_id) {
        Integer eventId = Integer.valueOf(event_id);  // convert String to Integer
        return statsService.getStatsByEventId(eventId);
    }

}
