//QRCodeGeneratorService
package com.example.demo.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64; // Correct import for Base64
import java.util.Date;

@Service
public class QRCodeGeneratorService {

    private static final Logger log = LoggerFactory.getLogger(QRCodeGeneratorService.class);
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final String DATE_FORMAT = "yyyyMMddHHmmss";

    @Value("${qrcode.output.directory:./qrcodes}")
    private String outputLocation;

    // Generates QR code and saves to file, returns file path
    public Path generateQRCodeAsFile(String message) throws WriterException, IOException {
        // Generate QR code bytes
        byte[] qrCodeBytes = generateQRCodeImageBytes(message);
        
        // Prepare output path
        Path outputPath = prepareOutputPath();
        
        // Ensure directory exists
        Files.createDirectories(outputPath.getParent());
        
        // Write to file
        Files.write(outputPath, qrCodeBytes);
        
        log.info("QR code saved to: {}", outputPath);
        return outputPath;
    }

    // Generates QR code as byte array (PNG format)
    public byte[] generateQRCodeImageBytes(String message) throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(message, BarcodeFormat.QR_CODE, WIDTH, HEIGHT);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "PNG", outputStream);
        return outputStream.toByteArray();
    }

    // Generates Base64 encoded string (for other use cases)
    public String generateQRCodeAsBase64(String message) {
        try {
            return Base64.getEncoder().encodeToString(generateQRCodeImageBytes(message));
        } catch (Exception e) {
            log.error("Error generating QR Base64", e);
            return null;
        }
    }

    // Prepares output file path with timestamp
    private Path prepareOutputPath() {
        String timestamp = new SimpleDateFormat(DATE_FORMAT).format(new Date());
        String fileName = "QRCode_" + timestamp + ".png";
        return Paths.get(outputLocation, fileName);
    }
}

//    private String prepareOutputFilename() {
//        String timestamp = new SimpleDateFormat(DATE_FORMAT).format(new Date());
//        return outputLocation + File.separator + "QRCode_" + timestamp + ".png";
//    }
//
//    private void createDirectoryIfNotExists(String directoryPath) {
//        File dir = new File(directoryPath);
//        if (!dir.exists()) {
//            boolean created = dir.mkdirs();
//            if (created) {
//                log.info("Created output directory: {}", directoryPath);
//            } else {
//                log.warn("Failed to create output directory: {}", directoryPath);
//            }
//        }
//    }
//
//    private void processQRCode(String data, String filePath, String charset, int width, int height)
//            throws WriterException, IOException {
//
//        BitMatrix matrix = new MultiFormatWriter()
//                .encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, width, height);
//
//        Path path = new File(filePath).toPath();
//        MatrixToImageWriter.writeToPath(matrix, "PNG", path);
//    }

