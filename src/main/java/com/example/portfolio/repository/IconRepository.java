package com.example.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.portfolio.entity.Icon;

import java.util.UUID;

@Repository
public interface IconRepository extends JpaRepository<Icon, UUID> {
}
