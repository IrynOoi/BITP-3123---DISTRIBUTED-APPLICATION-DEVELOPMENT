//QRCodeController .java
package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.QRCodeGeneratorService;
import com.google.zxing.WriterException;

import java.io.IOException;

@RestController
public class QRCodeController {

    private static final Logger log = LoggerFactory.getLogger(QRCodeController.class);

    @Autowired
    private QRCodeGeneratorService qrCodeGeneratorService;

    @GetMapping(value = "/api/generateQR", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> generateQRCode(
            @RequestParam("event_id") String eventId,
            @RequestParam("qr_token") String qrToken) {

        try {
            // Build a check-in URL including both eventId and qrToken
            String baseUrl = "http://192.168.0.10:8080/eventgo_db/checkin";
            String checkInUrl = baseUrl + "?eventId=" + eventId + "&qrToken=" + qrToken;


            // Generate QR code bytes
            byte[] qrCodeBytes = qrCodeGeneratorService.generateQRCodeImageBytes(checkInUrl);

            ByteArrayResource resource = new ByteArrayResource(qrCodeBytes);

            return ResponseEntity.ok()
                    .contentLength(qrCodeBytes.length)
                    .contentType(MediaType.IMAGE_PNG)
                    .body(resource);

        } catch (WriterException | IOException e) {
            log.error("QR generation failed", e);
            return ResponseEntity.status(500).body(null);
        }
    }
}
