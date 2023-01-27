package com.pizzashop.pizzashop.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import jakarta.json.Json;

public class RedisRepository {
    @Autowired
    RedisTemplate<String, Object> redisTemplate; 
    public String insertRecord(String id, String record) {

        // Insert Record
        redisTemplate.opsForValue().set(id, record);        

        String payload = Json.createObjectBuilder()
                                        .add("id", id)
                                        .build()
                                        .toString();
       return payload;
   }
    public Optional<String> getRecordById(String id) {
        return null;
    }
}
