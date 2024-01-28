package com.example.medhub.mapper;

import com.example.medhub.dto.request.UserCreateRequestDto;
import com.example.medhub.dto.UserDto;
import com.example.medhub.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class UserMapper {
    public static final UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "password", source = "encryptedPassword")
    public abstract UserEntity toUser(UserCreateRequestDto createRequestDto, String encryptedPassword);

    public abstract UserDto toUserDto(UserEntity savedUser);
}
