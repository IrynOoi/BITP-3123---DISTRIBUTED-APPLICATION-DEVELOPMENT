//QRCodeGeneratorService
package com.example.demo.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
public class QRCodeGeneratorService {

    private static final Logger log = LoggerFactory.getLogger(QRCodeGeneratorService.class);

    private static final String CHARSET = "UTF-8";
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final String DATE_FORMAT = "yyyyMMddHHmmss";

    // Inject output directory from application.properties (e.g. qrcode.output.directory=./qrcodes)
    @Value("${qrcode.output.directory:./qrcodes}")
    private String outputLocation;

    public void generateQRCode(String message) {
        log.info("### Generating QR Code ###");
        try {
            if (StringUtils.isBlank(message)) {
                log.warn("Empty or blank message received for QR code generation.");
                return;
            }

            String outputPath = prepareOutputFilename();
            createDirectoryIfNotExists(outputLocation);
            processQRCode(message, outputPath, CHARSET, WIDTH, HEIGHT);
            log.info("QR Code generated at {}", outputPath);
        } catch (WriterException | IOException e) {
            log.error("Error while generating QR Code", e);
        }
    }

    private String prepareOutputFilename() {
        String timestamp = new SimpleDateFormat(DATE_FORMAT).format(new Date());
        return outputLocation + File.separator + "QRCode_" + timestamp + ".png";
    }

    private void createDirectoryIfNotExists(String directoryPath) {
        File dir = new File(directoryPath);
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (created) {
                log.info("Created output directory: {}", directoryPath);
            } else {
                log.warn("Failed to create output directory: {}", directoryPath);
            }
        }
    }

    private void processQRCode(String data, String filePath, String charset, int width, int height)
            throws WriterException, IOException {

        BitMatrix matrix = new MultiFormatWriter()
                .encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, width, height);

        Path path = new File(filePath).toPath();
        MatrixToImageWriter.writeToPath(matrix, "PNG", path);
    }
}
