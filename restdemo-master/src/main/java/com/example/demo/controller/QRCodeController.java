//QRCodeController .java
package com.example.demo.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.QRCodeGeneratorService;

@RestController
public class QRCodeController {

    private static final Logger log = LoggerFactory.getLogger(QRCodeController.class);

    @Autowired
    private QRCodeGeneratorService qrCodeGeneratorService;

    @PostMapping("/qrcode")
    public String addUser(@RequestHeader String message) {
        log.info("Input Message is {}", message);
        qrCodeGeneratorService.generateQRCode(message);
        return "Created QR Code";
    }
}
