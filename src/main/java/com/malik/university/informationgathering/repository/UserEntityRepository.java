package com.malik.university.informationgathering.repository;

import com.malik.university.informationgathering.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends CrudRepository<UserEntity, Integer> {

    UserEntity findByTelegramUserName(String telegramUserName);
}
