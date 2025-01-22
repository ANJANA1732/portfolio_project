package com.example.portfolio.repository;

import com.example.portfolio.entity.ContactForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactFormRepo  extends JpaRepository<ContactForm,Long> {
}
