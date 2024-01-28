package com.example.medhub.dto;

import com.example.medhub.entity.UserEntity;
import com.example.medhub.mapper.UserMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserDto {
    private final Long userId;

    private final String name;

    private final String surname;

    private final String email;

    private final String password;

    private final String phoneNumber;

    public static UserDto from(UserEntity userEntity) {
        return UserMapper.USER_MAPPER.toUserDto(userEntity);
    }
}
