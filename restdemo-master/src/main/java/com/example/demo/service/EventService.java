//EventService.java
package com.example.demo.service;

import com.example.demo.model.Event;
import com.example.demo.model.PublishActivityRequest;
import com.example.demo.repository.EventRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    // Constructor injection - make sure the constructor name matches the class name
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents()
    {
        return eventRepository.findAll();
    }
    
  
  	 public List<Event> getAllEvent()
  	 {
  		List <Event> events = eventRepository.findAll();
  		return events;
  	 }
  	 
//  	public String getQR_Token(Integer eventId) {
//  	    return eventRepository.findById(eventId)
//  	            .map(Event::getQrToken)
//  	            .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));
//  	}
//    
    
    
    
  
  

        public Event createEvent(PublishActivityRequest request) {
            Event event = new Event();

            event.setTitle(request.getTitle());
            event.setDescription(request.getDescription());
            event.setStartDateTime(request.getStartDateTime());
            event.setEndDateTime(request.getEndDateTime());
            event.setLocation(request.getLocation());
            event.setCapacity(request.getCapacity());
            event.setUserId(request.getUserId());

            event.setCreatedAt(LocalDateTime.now());
//            event.setFirestorePath("events/firestore/path/" + System.currentTimeMillis());

            return eventRepository.save(event);
        }
        
        
   	 // Constructor injection - make sure the constructor name matches the class name
   	 
    

}