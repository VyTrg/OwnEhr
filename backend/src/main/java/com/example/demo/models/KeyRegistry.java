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
public class KeyRegistry {
    @Id
    private String keyId;
    private UUID ownerAlias;
    private Instant createdAt = Instant.now();
    private boolean revoked = false;
}
