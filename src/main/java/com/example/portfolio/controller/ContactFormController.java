package com.example.portfolio.controller;

import com.example.portfolio.entity.ContactForm;
import com.example.portfolio.service.ContactFormServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactFormController {

    private final ContactFormServ contactFormServ;

    @Autowired
    public ContactFormController(ContactFormServ contactFormServ){
        this.contactFormServ = contactFormServ;
    }

    @PostMapping
    public ResponseEntity<String> submitContactForm(@RequestBody ContactForm contactForm) {
        ContactForm savedContactForm = contactFormServ.saveContactForm(contactForm);
        return new ResponseEntity<>("Message sent successfully!", HttpStatus.CREATED);
    }

}
