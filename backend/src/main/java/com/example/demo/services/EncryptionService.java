package com.example.demo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Map;

@Service
public class EncryptionService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public byte[] encrypt(Map<String, Object> phiFields, SecretKey key) throws Exception {
        String json = objectMapper.writeValueAsString(phiFields);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(json.getBytes());
    }

    public SecretKey generateSecretKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        return keyGen.generateKey();
    }

    public SecretKey mockSecretKeyFromFabric(String keyId) {
        // Fake static key from keyId
        byte[] keyBytes = keyId.substring(0, 16).getBytes();
        return new SecretKeySpec(keyBytes, "AES");
    }
}
