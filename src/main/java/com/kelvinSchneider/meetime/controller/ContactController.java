package com.kelvinSchneider.meetime.controller;

import com.kelvinSchneider.meetime.model.Contact;
import com.kelvinSchneider.meetime.service.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<String> createContact(@RequestBody Contact contact) {
        String response = contactService.createContact(contact);
        return ResponseEntity.ok(response);
    }
}
