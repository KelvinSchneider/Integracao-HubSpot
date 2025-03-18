package com.kelvinSchneider.meetime.service;

import com.kelvinSchneider.meetime.cache.TokenCache;
import com.kelvinSchneider.meetime.exception.InvalidTokenException;
import com.kelvinSchneider.meetime.exception.RateLimitExceededException;
import com.kelvinSchneider.meetime.model.Contact;
import com.kelvinSchneider.meetime.request.ContactRequest;
import com.kelvinSchneider.meetime.util.RateLimiter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Service
public class ContactService {

    @Value("${hubspot.api.url}")
    private String apiUrl;

    private static final String pathContact = "/crm/v3/objects/contacts";

    private final TokenCache tokenCache;
    private final RestTemplate restTemplate;
    private final RateLimiter rateLimiter;

    public ContactService(RestTemplate restTemplate, TokenCache tokenCache, RateLimiter rateLimiter) {
        this.restTemplate = restTemplate;
        this.tokenCache = tokenCache;
        this.rateLimiter = rateLimiter;
    }

    public String createContact(Contact contact) {
        if (!rateLimiter.tryAcquire()) {
            throw new RateLimitExceededException("Rate limit exceeded. Try again later.");
        }
        String accessToken = getAccessToken();

        ContactRequest contactRequest = new ContactRequest();
        contactRequest.addProperty("email", contact.getEmail());
        contactRequest.addProperty("firstname", contact.getFirstName());
        contactRequest.addProperty("lastname", contact.getLastName());
        contactRequest.addProperty("phone", contact.getPhone());

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ContactRequest> request = new HttpEntity<>(contactRequest, headers);
        return restTemplate.postForObject(apiUrl + pathContact, request, String.class);
    }

    private String getAccessToken() {
        String accessToken = tokenCache.getAccessToken();
        if (accessToken == null) {
            throw new InvalidTokenException("Access token not found in cache.");
        }
        return accessToken;
    }
}
