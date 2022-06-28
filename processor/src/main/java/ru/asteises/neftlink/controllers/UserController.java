package ru.asteises.neftlink.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.asteises.neftlink.dto.UserDto;
import ru.asteises.neftlink.service.UserService;

import java.util.UUID;

/**
REST определяет стиль взаимодействия (обмена данными) между разными компонентами системы. Аннотация @RestController
в Spring MVC – это не что иное, как сочетание аннотации @Controller и @ResponseBody.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     *Принимает запрос на создание нового объекта типа User в базу данных с помощью Service
     */
    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody UserDto userDto) {
        userService.registration(userDto);
        return ResponseEntity.ok("запрос на создание нового объекта User в базу данных успешно принят и обработан");
    }

    @PutMapping("/change/{id}")
    public ResponseEntity<String> put(@RequestBody UserDto userDto, @PathVariable UUID id) {
        return userService.put(userDto, id);
    }
}