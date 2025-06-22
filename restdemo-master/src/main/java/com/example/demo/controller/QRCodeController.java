//QRCodeController .java
package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.QRCodeGeneratorService;
import java.io.IOException;
import com.google.zxing.WriterException;

@RestController
public class QRCodeController {

    private static final Logger log = LoggerFactory.getLogger(QRCodeController.class);

    @Autowired
    private QRCodeGeneratorService qrCodeGeneratorService;

    @GetMapping(value = "/qrcode", produces = "image/png")
    public ResponseEntity<Resource> generateUserQRCode(
            @RequestParam String userId, 
            @RequestParam String eventId) {
        
        try {
            // Create check-in URL
            String checkInUrl = String.format("http://yourappdomain.com/checkin/qr?userId=%s&eventId=%s", 
                                         userId, eventId);
            log.info("Generating QR Code for URL: {}", checkInUrl);
            
            // Generate QR code bytes
            byte[] qrCodeBytes = qrCodeGeneratorService.generateQRCodeImageBytes(checkInUrl);
            
            // Prepare response
            ByteArrayResource resource = new ByteArrayResource(qrCodeBytes);
            
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=qrcode.png")
                    .header("Content-Type", "image/png")
                    .contentLength(qrCodeBytes.length)
                    .body(resource);
        } catch (WriterException | IOException e) {
            log.error("QR generation failed", e);
            return ResponseEntity.status(500).body(null);
        } catch (Exception e) {
            log.error("Unexpected error during QR generation", e);
            return ResponseEntity.status(500).body(null);
        }
    }
}