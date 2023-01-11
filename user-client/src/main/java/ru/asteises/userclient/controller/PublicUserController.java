package ru.asteises.userclient.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.asteises.userclient.dto.UserDto;
import ru.asteises.userclient.dto.RegistrationViewDto;
import ru.asteises.userclient.service.UserPublicService;

/**
 * Действия пользователей без авторизации.
 * <p>
 * 1. Регистрация. Любой пользователь может пройти процедуру регистрации.
 * Первоначальная регистрация, это регистрация именно Мастер-аккаунта для управления всем приложением.
 * <p>
 * <p>
 * 2. Просмотр главной страницы.
 */
@Slf4j
@RestController
@RequestMapping("/")
public class PublicUserController {

    private UserPublicService userPublicService;

    /**
     * Метод для регистрации Мастер-аккаунта пользователя.
     *
     * @param userDto #{@link UserDto}
     * @return RegistrationViewDto #{@link RegistrationViewDto}
     */
    @PostMapping("/registration")
    public ResponseEntity<RegistrationViewDto> userRegistration(@RequestBody UserDto userDto) {

        return userPublicService.userRegistration(userDto);
    }

    // TODO Что нужно возвращать при запросе главной страницы ИМ?
    @GetMapping
    public ResponseEntity<String> getFirstPage() {
        return ResponseEntity.ok("OK");
    }
}
