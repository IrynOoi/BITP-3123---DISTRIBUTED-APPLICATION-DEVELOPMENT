//PublishEventController.java
package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.PublishActivityRequest;
import com.example.demo.service.EventService;

@RestController
@RequestMapping("/api")
public class PublishEventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/publish")
    public ResponseEntity<String> publishActivity(@RequestBody PublishActivityRequest request) {
        try {
            eventService.createEvent(request);
            return ResponseEntity.ok("Activity published successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to publish activity: " + e.getMessage());
        }
    }
}