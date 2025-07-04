package com.example.demo.repositories;

import com.example.demo.models.PhiMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhiMappingRepository extends JpaRepository<PhiMapping, UUID> {
}
