package com.example.demo.repositories;

import com.example.demo.models.DemographicProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DemographicRepository extends JpaRepository<DemographicProfile, UUID> {
}
