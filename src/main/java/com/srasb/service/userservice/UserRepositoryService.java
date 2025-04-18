package com.srasb.service.userservice;

import com.srasb.model.entity.UserEntity;
import com.srasb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRepositoryService {

    private final UserRepository userRepository;


    public void add(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
