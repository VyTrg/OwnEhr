package com.example.demo.services;

import com.example.generated.vitalsignsmonitoringcomposition.VitalSignsMonitoringComposition;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import org.ehrbase.openehr.sdk.client.openehrclient.OpenEhrClient;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CompositionService {
    private final OpenEhrClient client;

    public CompositionService(OpenEhrClient client) {
        this.client = client;
    }

    public VitalSignsMonitoringComposition fetchComposition(String ehrId, String versionUidStr) {
        UUID ehrUuid = UUID.fromString(ehrId);
        UUID versionUid = UUID.fromString(versionUidStr);

        System.out.println("Fetching composition with:");
        System.out.println("EHR UUID: " + ehrUuid);
        System.out.println("Version UID: " + versionUid);

        Optional<VitalSignsMonitoringComposition> composition =
                client.compositionEndpoint(ehrUuid)
                        .find(versionUid, VitalSignsMonitoringComposition.class);

        if (composition.isEmpty()) {
            System.err.println("Composition not found with versionUid=" + versionUid);
            throw new RuntimeException("Composition not found");
        }

        System.out.println("Composition fetched successfully");
        return composition.get();
    }
}
