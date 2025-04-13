package com.srasb.service.userservice;

import com.srasb.model.entity.UserEntity;
import com.srasb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRepositoryService {

    private final UserRepository userRepository;


    public void add(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    public boolean findByName(String username) {
        return userRepository.findByUsername(username).isPresent();
    }


}
