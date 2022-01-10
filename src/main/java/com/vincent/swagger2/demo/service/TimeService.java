package com.vincent.swagger2.demo.service;


import java.time.LocalDateTime;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TimeService {

    @Cacheable(cacheNames = "getTime")
    public LocalDateTime getTime() {
        return LocalDateTime.now();
    }

    @Cacheable("currentTimeMillis")
    public Long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
}
