package com.kelvinSchneider.meetime.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kelvinSchneider.meetime.exception.WebhookException;
import com.kelvinSchneider.meetime.model.WebhookHubspot;
import org.springframework.stereotype.Service;

@Service
public class WebhookService {

    public static final String eventContactCreation = "contact.creation";

    public String processWebhook(String payload) {
        try {
            payload = payload.replace("[", "").replace("]", "");
            ObjectMapper objectMapper = new ObjectMapper();
            WebhookHubspot webhookPayload = objectMapper.readValue(payload, WebhookHubspot.class);

            String event = "";
            if (eventContactCreation.equals(webhookPayload.getSubscriptionType())) {
                event = webhookPayload.toString();
            }
            return event;
        } catch (Exception e) {
            throw new WebhookException("Error processing webhook: ", e.getCause());
        }
    }
}
