package ru.asteises.neftlink.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.asteises.neftlink.dto.UserChangeDto;
import ru.asteises.neftlink.dto.UserDto;
import ru.asteises.neftlink.securityConfig.jwt.JwtRequest;
import ru.asteises.neftlink.securityConfig.jwt.JwtResponse;
import ru.asteises.neftlink.service.AuthService;
import ru.asteises.neftlink.service.UserService;

import javax.security.auth.message.AuthException;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * REST определяет стиль взаимодействия (обмена данными) между разными компонентами системы. Аннотация @RestController
 * в Spring MVC – это не что иное, как сочетание аннотации @Controller и @ResponseBody.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final AuthService authService;

    /**
     * Принимает запрос на создание нового объекта типа User в базу данных с помощью Service
     */
    @PostMapping("/registration")
    public ResponseEntity<JwtResponse> registration(@RequestBody UserDto userDto) throws AuthException {
        userService.registration(userDto);
        JwtRequest jwtRequest = new JwtRequest(userDto.getEmail().toString(), userDto.getPassword());
        return ResponseEntity.ok(authService.login(jwtRequest));
    }

    //TODO Предполагаю, что смена пароля, например, должна через отдельный метод происходить?
    @PutMapping("/change/{id}")
    public ResponseEntity<String> put(@RequestBody @NotNull UserChangeDto userChangeDto, @PathVariable UUID id) {
        return userService.put(userChangeDto, id);
    }
}