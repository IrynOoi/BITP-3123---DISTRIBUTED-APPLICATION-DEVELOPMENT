package com.example.demo;

import com.example.demo.service.QRCodeGeneratorService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestdemoApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RestdemoApplication.class);

    private final QRCodeGeneratorService qrCodeGeneratorService;

    @Autowired
    public RestdemoApplication(QRCodeGeneratorService qrCodeGeneratorService) {
        this.qrCodeGeneratorService = qrCodeGeneratorService;
    }

    public static void main(String[] args) {
        SpringApplication.run(RestdemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // Run in a new thread to avoid blocking the web server
        new Thread(() -> {
            try {
                log.info("Enter your Message:");
                String inputMessage = new java.util.Scanner(System.in).nextLine();
                log.info("Input Message from console - {}", inputMessage);
                if (StringUtils.isNotBlank(inputMessage)) {
                    qrCodeGeneratorService.generateQRCodeAsBase64(inputMessage);
                }
            } catch (Exception e) {
                log.error("Error reading input: ", e);
            }
        }).start();
    }
}
