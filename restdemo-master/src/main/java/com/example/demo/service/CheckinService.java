// CheckinService.java
package com.example.demo.service;

import com.example.demo.repository.CheckinRepository;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
}
