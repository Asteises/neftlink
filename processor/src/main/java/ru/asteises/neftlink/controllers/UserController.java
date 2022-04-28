package ru.asteises.neftlink.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.asteises.neftlink.entity.Users;
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

    @GetMapping("/user/all")
    public List<Users> allUsers() {
        return null;
    }
}
