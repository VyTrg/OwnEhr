package com.example.demo.services;

import com.example.generated.vitalsignsmonitoringcomposition.VitalSignsMonitoringComposition;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import com.example.demo.config.EhrClientProvider;
import org.ehrbase.openehr.sdk.client.openehrclient.OpenEhrClient;
import org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient.DefaultRestCompositionEndpoint;
import org.ehrbase.openehr.sdk.response.dto.CompositionResponseData;
import org.ehrbase.openehr.sdk.serialisation.jsonencoding.CanonicalJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class EhrService {

    private final OpenEhrClient client;
    private final ObjectMapper mapper = new ObjectMapper();

    public EhrService(OpenEhrClient client) {
        this.client = client;
    }
}
