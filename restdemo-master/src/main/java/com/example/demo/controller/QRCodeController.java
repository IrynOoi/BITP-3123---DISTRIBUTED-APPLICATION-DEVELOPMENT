//QRCodeController .java
package com.example.demo.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.QRCodeGeneratorService;

@RestController
public class QRCodeController {

    private static final Logger log = LoggerFactory.getLogger(QRCodeController.class);

    @Autowired
    private QRCodeGeneratorService qrCodeGeneratorService;

    @PostMapping("/qrcode")
    public ResponseEntity<String> generateUserQRCode(@RequestParam String userId, @RequestParam String eventId) {
        String checkInUrl = "http://localhost:8080/checkin?eventId=" + eventId + "&userId=" + userId;
        log.info("Generating QR Code for URL: {}", checkInUrl);
        qrCodeGeneratorService.generateQRCodeAsBase64(checkInUrl);
        return ResponseEntity.ok("QR Code created for " + userId + " in event " + eventId);
    }

}
