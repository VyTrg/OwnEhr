package com.example.demo.controllers;

import com.example.generated.vitalsignsmonitoringcomposition.VitalSignsMonitoringComposition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.CompositionService;
import com.example.demo.services.EhrService;
import com.example.demo.services.FilteredCompositionBuilder;

@RestController
@RequestMapping("/api/vitals")
public class VitalsController {
    private final CompositionService compService;
    private final FilteredCompositionBuilder builder;

    public VitalsController(CompositionService compService, FilteredCompositionBuilder builder) {
        this.compService = compService;
        this.builder = builder;
    }

    @GetMapping("/{ehrId}/{compId}")
    public ResponseEntity<?> getFiltered(@PathVariable String ehrId,
                                         @PathVariable String compId,
                                         @RequestParam String username) {

        VitalSignsMonitoringComposition comp = compService.fetchComposition(ehrId, compId);
        var filtered = builder.filter(comp, username);
        return ResponseEntity.ok(filtered);
    }
}
