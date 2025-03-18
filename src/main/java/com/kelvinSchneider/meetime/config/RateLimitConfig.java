package com.kelvinSchneider.meetime.config;

import com.kelvinSchneider.meetime.util.RateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimitConfig {

    @Bean
    public RateLimiter rateLimiter() {
        return new RateLimiter(110, 10);
    }
}