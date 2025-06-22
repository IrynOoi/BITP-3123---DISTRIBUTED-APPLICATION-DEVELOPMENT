// CheckinService.java
package com.example.demo.service;

import com.example.demo.repository.CheckinRepository;
import com.google.common.base.Optional;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import antlr.debug.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

@Service
public class CheckinService {

    @Autowired
    private CheckinRepository checkinRepository;

    public void markUserCheckedIn(String userIdStr, String eventIdStr) {
        int userId = Integer.parseInt(userIdStr);
        int eventId = Integer.parseInt(eventIdStr);

       checkinRepository.checkinUserInMySQL(userId, eventId);
       syncCheckinToFirebase(eventIdStr, userIdStr);
    }

    public void syncCheckinToFirebase(String eventIdStr, String userIdStr) {
        DatabaseReference firebaseRef = FirebaseDatabase.getInstance().getReference();
        firebaseRef.child("event-qrcodes").child(eventIdStr)
            .child("checkins").child(userIdStr)
            .setValueAsync(LocalDateTime.now().toString());
    }
    
 // New QR-based check-in processing
    @Transactional
    public void processQRCheckIn(String userId, String qrToken) {
        int userIdInt = Integer.parseInt(userId);
        checkinRepository.checkinByQRToken(userIdInt, qrToken);
    }
}
