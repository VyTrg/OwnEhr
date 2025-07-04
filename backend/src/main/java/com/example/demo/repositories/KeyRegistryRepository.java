package com.example.demo.repositories;

import com.example.demo.models.KeyRegistry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KeyRegistryRepository extends JpaRepository<KeyRegistry, UUID> {
}
