package com.kelvinSchneider.meetime.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

@Component
public class TokenCache {

    @Cacheable(value = "tokenCache", key = "'accessToken'")
    public String cacheToken(String token) {
        return token;
    }

    @Cacheable(value = "tokenCache", key = "'accessToken'")
    public String getAccessToken() {
        return null;
    }

    @CacheEvict(value = "tokenCache", key = "'accessToken'")
    public void clearTokenCache() {
        System.out.println("Token cache cleared.");
    }
}
