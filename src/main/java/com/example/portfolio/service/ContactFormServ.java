package com.example.portfolio.service;

import com.example.portfolio.entity.ContactForm;
import com.example.portfolio.repository.ContactFormRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactFormServ {

    private ContactFormRepo contactFormRepo;
    @Autowired
    public ContactFormServ(ContactFormRepo contactFormRepo){
        this.contactFormRepo = contactFormRepo;
    }
    public ContactForm saveContactForm(ContactForm contactForm){
        return contactFormRepo.save(contactForm);
    }
}
