package com.malik.university.informationgathering.repository;

import com.malik.university.informationgathering.entity.ApiKeyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiKeyEntityRepository extends CrudRepository<ApiKeyEntity, Integer> {
}
