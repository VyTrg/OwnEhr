package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;



import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
public class DemographicProfile {
    @Id
    private UUID aliasId;
    private String gender;
    private Integer birthYear;
    private String ethnicity;
    private String insuranceProvider;
    private Instant createdAt = Instant.now();
    private Instant updatedAt;
}
