package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


import java.util.UUID;

@Entity
@Getter
@Setter
public class PhiMapping {
    @Id
    private UUID aliasId;
    private byte[] encryptedPhi;
    private String hashPhi;
    private String encryptionKeyId;
}
