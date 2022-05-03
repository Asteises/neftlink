package ru.asteises.neftlink.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.asteises.neftlink.dto.RoleDto;
import ru.asteises.neftlink.entity.Role;
import ru.asteises.neftlink.service.RoleService;
import ru.asteises.neftlink.service.UserService;

@RestController
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;

    public RoleController (RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     *Принимает запрос на создание нового объекта типа Role в базу данных с помощью Service
     */
    @PostMapping("/add")
    public ResponseEntity<String> add(RoleDto roleDto) {
        roleService.add(roleDto);
        return ResponseEntity.ok("запрос на создание нового объекта Role в базу данных успешно принят и обработан");
    }

}
