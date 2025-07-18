////EventController.java
//package com.example.demo.controller;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
//import java.util.List;
//
//import javax.imageio.ImageIO;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.server.ResponseStatusException;
//
//import com.example.demo.service.EventService;
//import com.example.demo.service.QRService;
//import com.google.zxing.WriterException;
//import com.example.demo.model.Event;
//
//
//@RestController
//@RequestMapping("/api/events")
//public class EventController1 {
//
//	
//	private final EventService eventService;
//	private final QRService qrService;
//	
//	public EventController1(EventService eventService,QRService qrService) {
//			
//		this.eventService = eventService;
//		this.qrService = qrService;
//	}
//	
//	@GetMapping
//	public List<Event> getAllEvent()
//	{
//		List<Event> events = eventService.getAllEvent();
//		return events;
//	}
//	
//	@GetMapping(value = "/QRCode")
//	public  ResponseEntity<byte[]> getEventQRCodeImage(@RequestParam Integer eventId) throws WriterException
//	{
//		try {
//			String qrToken = eventService.getQR_Token(eventId);	
//	        if (qrToken == null || qrToken.isBlank()) {
//	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "QR Token not found for event ID: " + eventId);
//	        }
//
//	        BufferedImage image = qrService.generateQRCodeImage(qrToken);
//
//	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//	        ImageIO.write(image, "png", baos);
//
//	        return ResponseEntity.ok()
//	                .contentType(MediaType.IMAGE_PNG)
//	                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"event-" + eventId + ".png\"")
//	                .body(baos.toByteArray());
//
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "QR code generation failed", e);
//	    }
//	}
//}
