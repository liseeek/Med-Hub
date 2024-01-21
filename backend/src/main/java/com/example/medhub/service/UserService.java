package com.example.medhub.service;

import com.example.medhub.dto.create.UserCreateRequestDto;
import com.example.medhub.dto.UserDto;
import com.example.medhub.entity.User;
import com.example.medhub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto saveUser(UserCreateRequestDto newUser){
        User user = User.from(newUser);
        User savedUser = userRepository.save(user);
        return UserDto.from(savedUser);
    }
}
