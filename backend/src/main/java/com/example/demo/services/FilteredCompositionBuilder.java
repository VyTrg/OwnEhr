package com.example.demo.services;


import com.example.generated.vitalsignsmonitoringcomposition.VitalSignsMonitoringComposition;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FilteredCompositionBuilder {

    private final PrivacyRuleService ruleService;

    public FilteredCompositionBuilder(PrivacyRuleService ruleService) {
        this.ruleService = ruleService;
    }

    public Map<String, Object> filter(VitalSignsMonitoringComposition comp, String username) {
        Map<String, Object> result = new HashMap<>();

        if (ruleService.canAccess(username, "bodyTemperature") && comp.getBodyTemperature() != null)
            result.put("bodyTemperature", comp.getBodyTemperature());

        if (ruleService.canAccess(username, "pulseHeartBeat") && comp.getPulseHeartBeat() != null)
            result.put("pulseHeartBeat", comp.getPulseHeartBeat());

        if (ruleService.canAccess(username, "bloodPressure") && comp.getBloodPressure() != null)
            result.put("bloodPressure", comp.getBloodPressure());

        if (ruleService.canAccess(username, "composer"))
            result.put("composer", comp.getComposer());

        if (ruleService.canAccess(username, "location"))
            result.put("location", comp.getLocation());

        if (ruleService.canAccess(username, "participations"))
            result.put("participations", comp.getParticipations());

        if (ruleService.canAccess(username, "healthCareFacility"))
            result.put("healthCareFacility", comp.getHealthCareFacility());

        if (ruleService.canAccess(username, "territory"))
            result.put("territory", comp.getTerritory());

        return result;
    }
}
