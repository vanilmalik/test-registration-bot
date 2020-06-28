package com.malik.university.informationgathering.service.impl;

import com.malik.university.informationgathering.entity.UserEntity;
import com.malik.university.informationgathering.repository.UserEntityRepository;
import com.malik.university.informationgathering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.nonNull;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Transactional
    @Override
    public UserEntity doSave(UserEntity userEntity) {
        UserEntity foundUser = userEntityRepository.findByTelegramUserName(userEntity.getTelegramUserName());
        if (nonNull(foundUser)) {
            userEntity.setId(foundUser.getId());
        }

        return userEntityRepository.save(userEntity);
    }
}
