// RestdemoApplication.java

package com.example.demo;

import com.example.demo.service.QRCodeGeneratorService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class RestdemoApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RestdemoApplication.class);
    private final QRCodeGeneratorService qrCodeGeneratorService;

    // Constructor-based dependency injection
    public RestdemoApplication(QRCodeGeneratorService qrCodeGeneratorService) {
        this.qrCodeGeneratorService = qrCodeGeneratorService;
    }

    public static void main(String[] args) {
        SpringApplication.run(RestdemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        log.info("Enter your Message :");
        String inputMessage = scanner.nextLine();

        log.info("Input Message from console - {}", inputMessage);
        if (StringUtils.isNotBlank(inputMessage)) {
            qrCodeGeneratorService.generateQRCode(inputMessage);
        }
    }
}
