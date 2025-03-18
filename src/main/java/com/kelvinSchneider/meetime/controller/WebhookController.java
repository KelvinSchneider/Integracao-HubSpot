package com.kelvinSchneider.meetime.controller;


import com.kelvinSchneider.meetime.service.WebhookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private final WebhookService webhookService;

    public WebhookController(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @PostMapping("/contact-creation")
    public ResponseEntity<String> handleContactCreation(@RequestBody String payload) {
        String event = webhookService.processWebhook(payload);
        return ResponseEntity.ok("New contact created! " + event);
    }
}
