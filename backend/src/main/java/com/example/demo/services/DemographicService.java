package com.example.demo.services;

import com.example.demo.DTOs.RegisterRequestDTO;
import com.example.demo.models.DemographicProfile;
import com.example.demo.models.KeyRegistry;
import com.example.demo.models.PhiMapping;
import com.example.demo.repositories.DemographicRepository;
import com.example.demo.repositories.KeyRegistryRepository;
import com.example.demo.repositories.PhiMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class DemographicService {
    @Autowired
    private FabricKeyService fabricKeyService;
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private DemographicRepository demoRepo;
    @Autowired
    private PhiMappingRepository phiRepo;
    @Autowired
    private KeyRegistryRepository keyRepo;

    @Transactional
    public UUID register(RegisterRequestDTO dto) throws Exception {
        UUID aliasId = UUID.randomUUID();
        String keyId = fabricKeyService.createKey(aliasId);

        Map<String, Object> phiMap = new HashMap<>();
        phiMap.put("full_name", dto.getFullName());
        phiMap.put("address", dto.getAddress());
        phiMap.put("email", dto.getEmail());
        phiMap.put("phone", dto.getPhone());

        byte[] encrypted = encryptionService.encrypt(phiMap, encryptionService.mockSecretKeyFromFabric(keyId));

        DemographicProfile profile = new DemographicProfile();
        profile.setAliasId(aliasId);
        profile.setBirthYear(dto.getBirthYear());
        profile.setGender(dto.getGender());
        profile.setEthnicity(dto.getEthnicity());
        profile.setInsuranceProvider(dto.getInsuranceProvider());

        PhiMapping phi = new PhiMapping();
        phi.setAliasId(aliasId);
        phi.setEncryptedPhi(encrypted);
        phi.setEncryptionKeyId(keyId);

        KeyRegistry key = new KeyRegistry();
        key.setKeyId(keyId);
        key.setOwnerAlias(aliasId);

        demoRepo.save(profile);
        phiRepo.save(phi);
        keyRepo.save(key);

        return aliasId;
    }
}
