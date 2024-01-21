package com.example.medhub.mapper;

import com.example.medhub.dto.create.UserCreateRequestDto;
import com.example.medhub.dto.UserDto;
import com.example.medhub.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class UserMapper {
    public static final UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "userId", ignore = true)
    public abstract User toUser(UserCreateRequestDto createRequestDto);

    public abstract UserDto toUserDto(User savedUser);
}
