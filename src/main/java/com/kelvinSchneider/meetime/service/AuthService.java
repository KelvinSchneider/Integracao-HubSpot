package com.kelvinSchneider.meetime.service;

import com.kelvinSchneider.meetime.cache.TokenCache;
import com.kelvinSchneider.meetime.response.OAuthTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class AuthService {

    @Value("${hubspot.client.id}")
    private String clientId;

    @Value("${hubspot.client.secret}")
    private String clientSecret;

    @Value("${hubspot.redirect.uri}")
    private String redirectUri;

    @Value("${hubspot.authorization.url}")
    private String authorizationUrl;

    @Value("${hubspot.scope.contacts}")
    private String scopes;

    @Value("${hubspot.token.url}")
    private String tokenUrl;

    private final TokenCache tokenCache;
    private final RestTemplate restTemplate;

    public AuthService(RestTemplate restTemplate, TokenCache tokenCache) {
        this.restTemplate = restTemplate;
        this.tokenCache = tokenCache;
    }

    public String getAuthorizationUrl() {
        return String.format("%s?client_id=%s&redirect_uri=%s&scope=%s&response_type=code",
                authorizationUrl, clientId, redirectUri, scopes);
    }

    public String exchangeCodeForToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("redirect_uri", redirectUri);
        body.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        OAuthTokenResponse response = restTemplate.postForObject(tokenUrl, request, OAuthTokenResponse.class);

        if (response != null) {
            String token = response.getAccess_token();
            tokenCache.cacheToken(token);
            return token;
        }

        return null;
    }
}
