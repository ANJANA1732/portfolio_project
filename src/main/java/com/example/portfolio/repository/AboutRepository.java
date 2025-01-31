package com.example.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.portfolio.entity.About;

import java.util.UUID;

@Repository
public interface AboutRepository extends JpaRepository<About, UUID> {
}
