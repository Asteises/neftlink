package ru.asteises.neftlink.securityConfig.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtRequest {

    private String login;
    private String password;

}
