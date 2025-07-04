package com.example.demo.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class PrivacyEnforcer {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static Map<String, Object> filter(Map<String, Object> composition, String role, String templateId) {
        try {
            InputStream is = new FileInputStream("src/main/resources/privacy-rules/" + templateId + ".json");
            JsonNode root = mapper.readTree(is);
            JsonNode fields = root.get("fields");

            for (Iterator<String> it = fields.fieldNames(); it.hasNext(); ) {
                String key = it.next();
                JsonNode config = fields.get(key);

                if ("PHI".equals(config.get("privacy").asText())) {
                    ArrayNode allowedRoles = (ArrayNode) config.get("access");
                    boolean allowed = StreamSupport.stream(allowedRoles.spliterator(), false)
                            .anyMatch(r -> r.asText().equals(role));

                    if (!allowed) {
                        composition.remove(key);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return composition;
    }
}
