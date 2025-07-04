package com.example.demo.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PrivacyRuleService {
    private final Map<String, List<String>> accessMap = Map.of(
            "bodyTemperature", List.of("doctor", "nurse"),
            "pulseHeartBeat", List.of("doctor"),
            "bloodPressure", List.of("doctor"),
            "composer", List.of("admin"),
            "location", List.of("admin"),
            "healthCareFacility", List.of("admin"),
            "language", List.of("admin"),
            "participations", List.of("admin"),
            "territory", List.of("doctor", "nurse")
    );

    public boolean canAccess(String username, String field) {
        return accessMap.getOrDefault(field, List.of()).contains(username);
    }
}
