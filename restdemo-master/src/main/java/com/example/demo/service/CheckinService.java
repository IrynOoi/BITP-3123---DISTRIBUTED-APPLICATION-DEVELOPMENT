// CheckinService.java
package com.example.demo.service;

import com.example.demo.repository.CheckinRepository;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

@Service
public class CheckinService {

    @Autowired
    private CheckinRepository checkinRepository;

    // Use int parameters for userId and eventId directly for type safety
    public void markUserCheckedIn(int userId, int eventId) {
        // Perform check-in in DB
        checkinRepository.checkinUserInMySQL(userId, eventId);

//        // Sync check-in info to Firebase
//        syncCheckinToFirebase(eventId, userId);
    }

    private void syncCheckinToFirebase(int eventId, int userId) {
        DatabaseReference firebaseRef = FirebaseDatabase.getInstance().getReference();
        firebaseRef.child("event-qrcodes")
                   .child(String.valueOf(eventId))
                   .child("checkins")
                   .child(String.valueOf(userId))
                   .setValueAsync(LocalDateTime.now().toString());
    }

    // QR-based check-in method with int userId
    @Transactional
    public void processQRCheckIn(int userId, String qrToken) {
        checkinRepository.checkinByQRToken(userId, qrToken);
    }
}
