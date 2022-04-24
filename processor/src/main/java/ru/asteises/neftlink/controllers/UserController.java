package ru.asteises.neftlink.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.asteises.neftlink.entitys.User;
import ru.asteises.neftlink.repositoryes.UserRepository;

import java.util.List;

/*
REST определяет стиль взаимодействия (обмена данными) между разными компонентами системы. Аннотация @RestController
в Spring MVC – это не что иное, как сочетание аннотации @Controller и @ResponseBody.
 */
@RestController
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user/all")
    public List<User> allUsers() {
        return userRepository.findAll();
    }
}
