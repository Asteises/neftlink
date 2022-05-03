package ru.asteises.neftlink.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.asteises.neftlink.dto.UserDto;
import ru.asteises.neftlink.entity.User;
import ru.asteises.neftlink.service.UserService;

import java.util.List;

/*
REST определяет стиль взаимодействия (обмена данными) между разными компонентами системы. Аннотация @RestController
в Spring MVC – это не что иное, как сочетание аннотации @Controller и @ResponseBody.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     *Принимает запрос на создание нового объекта типа User в базу данных с помощью Service
     */
    @PostMapping("/add")
    public ResponseEntity<String> add(UserDto userDto) {
        userService.add(userDto);
        return ResponseEntity.ok("запрос на создание нового объекта User в базу данных успешно принят и обработан");
    }
}
