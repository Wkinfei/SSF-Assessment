package com.pizzashop.pizzashop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.pizzashop.pizzashop.models.Pizza;

@Service
public class RedisService {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;  

    public void saveGame(final Pizza pizza) {
        System.out.println("pizza " + pizza.toJSON().toString());
        redisTemplate.opsForValue().set(pizza.getOrderId(), pizza.toJSON().toString());
        // String result = (String) redisTemplate.opsForValue().get(pizza.getOrderId());
        // if (result != null)
        //     return 1;
        // return 0;
    }
}
