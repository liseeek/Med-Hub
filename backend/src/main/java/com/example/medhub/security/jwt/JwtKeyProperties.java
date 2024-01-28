package com.example.medhub.security.jwt;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("jwt.secret")
class JwtKeyProperties {

    @NotNull
    private String key;

}
