package com.malik.university.informationgathering.service.impl;

import com.malik.university.informationgathering.entity.ApiKeyEntity;
import com.malik.university.informationgathering.repository.ApiKeyEntityRepository;
import com.malik.university.informationgathering.service.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {

    @Autowired
    private ApiKeyEntityRepository apiKeyEntityRepository;

    @Override
    public void doSave(ApiKeyEntity apiKeyEntity) {
        apiKeyEntityRepository.save(apiKeyEntity);
    }
}
