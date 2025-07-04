package com.example.demo.config;

import org.ehrbase.openehr.sdk.client.openehrclient.OpenEhrClient;
import org.ehrbase.openehr.sdk.client.openehrclient.OpenEhrClientConfig;
import org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient.DefaultRestClient;
import org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient.DefaultRestCompositionEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
public class EhrClientProvider { ;

    @Bean
    public OpenEhrClient openEhrClient() {
        return new DefaultRestClient(
                new OpenEhrClientConfig(URI.create("http://localhost:8080/ehrbase/"))
        );
    }
}
