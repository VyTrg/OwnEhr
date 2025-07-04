package com.example.demo.services;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FabricKeyService {
    public String createKey(UUID aliasId) {
        // TODO: Replace with real Fabric SDK call
        return "fabric-key-" + aliasId.toString();
    }
}
