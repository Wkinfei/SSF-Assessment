package com.pizzashop.pizzashop.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.json.Json;

public class RestController {
    @Autowired
    RedisRepository redis;

    @GetMapping(path="/order/{id}")
    public ResponseEntity<String> getID(@PathVariable String id) {

        // get response from repo
        Optional<String> response = redis.getRecordById(id);
        System.out.println("test \n\n\n\n\n\n");
        
        if (response.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Json.createObjectBuilder()
                            .add("message", "Order %s not found".formatted(id))
                            .build()
                            .toString());
        }

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response.get());
}
}
