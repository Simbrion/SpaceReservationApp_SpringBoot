package com.srasb.service.userservice;

import com.srasb.model.entity.UserEntity;
import com.srasb.model.securitymodel.CustomUserDetails;
import com.srasb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity =  userRepository.findByUsername(username)
                                               .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    return new CustomUserDetails(userEntity.getId(),
                                userEntity.getUsername(),
                                userEntity.getPassword(),
                                userEntity.getUserRole());

    }


}
