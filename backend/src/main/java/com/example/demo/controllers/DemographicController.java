package com.example.demo.controllers;


import com.example.demo.DTOs.RegisterRequestDTO;
import com.example.demo.services.DemographicService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/demographic")

public class DemographicController {
    @Autowired
    private DemographicService demographicService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO dto) {
        try {
            UUID aliasId = demographicService.register(dto);
            return ResponseEntity.ok(Map.of("aliasId", aliasId.toString()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
