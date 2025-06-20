//RestDemoController
package com.example.demo.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam; // for query params
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.objects.Person;
import com.example.demo.service.FirebaseService;

//use http://localhost:8080/
@RestController
public class RestDemoController {

    @Autowired
    FirebaseService firebaseService;

    @GetMapping("/getUserDetails")
    public Person getExample(@RequestParam String name) throws InterruptedException, ExecutionException {
        System.out.println("Received name: " + name);
        Person result = firebaseService.getUserDetails(name);
        System.out.println("Result from Firebase: " + result);
        return result;
    }



    // Corrected method signature with @RequestBody and return type
    @PostMapping("/createUser")
    public ResponseEntity<?> saveUserDetails(@RequestBody Person person) throws InterruptedException, ExecutionException {
        String result = firebaseService.saveUserDetails(person);
        return ResponseEntity.ok(result);
    }

    // Example: PUT /updateUser with JSON body
    @PutMapping("/updateUser/{name}")
    public ResponseEntity<?> updateUser(@PathVariable String name, @RequestBody Person person) throws InterruptedException, ExecutionException {
        person.setName(name); // force the person’s name to the one from the URL
        String result = firebaseService.updateUserDetails(person);
        return ResponseEntity.ok(result);
    }

    // Example: DELETE /deleteUser?name=someName
    @DeleteMapping("/deleteUser")
    public String deleteExample(@RequestParam String name) {
        return "Deleted User " + name;
    } 

    // Enhanced global exception handler
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<Object> handleAllExceptions(
        HttpMessageNotReadableException ex, WebRequest request
    ) {
        String error;

        if (ex.getMessage().contains("Required request body is missing")) {
            error = "MISSING_BODY: No request body was provided. Please include a JSON body";
        } else if (ex.getMessage().contains("JSON parse error")) {
            error = "INVALID_JSON: Malformed JSON payload. Error: " + ex.getMostSpecificCause().getMessage();
        } else {
            error = "UNKNOWN_ERROR: " + ex.getMessage();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("{\n"
                    + "  \"status\": \"ERROR\",\n"
                    + "  \"message\": \"" + error + "\",\n"
                    + "  \"details\": \"" + request.getDescription(false) + "\",\n"
                    + "  \"required_format\": {\n"
                    + "    \"name\": \"string\",\n"
                    + "    \"age\": \"string\",\n"
                    + "    \"city\": \"string\"\n"
                    + "  }\n"
                    + "}");
    }
}
