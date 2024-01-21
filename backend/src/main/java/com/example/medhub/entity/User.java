package com.example.medhub.entity;

import com.example.medhub.dto.create.UserCreateRequestDto;
import com.example.medhub.mapper.UserMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String phoneNumber;

    public static User from(UserCreateRequestDto newUser) {
        return UserMapper.USER_MAPPER.toUser(newUser);
    }
    // Additional constructors, getters and setters for all fields
}
