package com.example.demo.service;
import java.util.Map;
//FirebaseService.java
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.example.demo.objects.Person;
import com.example.demo.objects.QRCodeData;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import antlr.debug.Event;

@Service
public class FirebaseService {

    public String saveUserDetails(Person person) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture =
            dbFirestore.collection("users").document(person.getName()).set(person);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
    
    public Person getUserDetails(String name) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("users").document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        
        DocumentSnapshot document = future.get();
        
        if (document.exists()) {
            return document.toObject(Person.class);
        } else {
            return null;
        }
    }
    
    public String updateUserDetails(Person person) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture =dbFirestore.collection("users").document(person.getName()).update("age", person.getAge(), "city", person.getLocation());
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
    public DocumentSnapshot getEventById(String id) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("event-qrcodes").document(id);
        return docRef.get().get(); // Returns a snapshot
    }



    

}