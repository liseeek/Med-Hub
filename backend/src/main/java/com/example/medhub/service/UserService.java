package com.example.medhub.service;

import com.example.medhub.dto.request.UserCreateRequestDto;
import com.example.medhub.dto.UserDto;
import com.example.medhub.entity.UserEntity;
import com.example.medhub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto saveUser(UserCreateRequestDto newUser){
        var encryptedPassword = passwordEncoder.encode(newUser.getPassword());
        UserEntity userEntity = UserEntity.from(newUser, encryptedPassword);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return UserDto.from(savedUserEntity);
    }

    public UserEntity getUser(String email) {
        return findByEmailOrThrow(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return findByEmailOrThrow(email);
    }

    private UserEntity findByEmailOrThrow(String email) {
        return userRepository.findUserEntitiesByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email=%s not found".formatted(email)));
    }
}
