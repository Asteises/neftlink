package ru.asteises.neftlink.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.asteises.neftlink.dto.BaseDto;
import ru.asteises.neftlink.entity.Base;
import ru.asteises.neftlink.service.BaseService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/base")
public class BaseController {

    private final BaseService baseService;

    public BaseController(BaseService baseService) {
        this.baseService = baseService;
    }

    /**
     *Принимает запрос на создание нового объекта типа Base в базу данных с помощью Service (РАБОТАЕТ)
     */
    @PostMapping("/add")
    @PreAuthorize(value = "@authService.authInfo.hasRole('ADMIN')")
    public ResponseEntity<String> add(@RequestBody BaseDto baseDto) {
        baseService.add(baseDto);
        return ResponseEntity.ok("запрос на создание нового объекта Base в базу данных успешно принят и обработан");
    }

    /**
     * Изменить существующий Base (РАБОТАЕТ)
     */
    @PutMapping("/change/{id}")
    @PreAuthorize(value = "@authService.authInfo.hasRole('ADMIN')")
    public ResponseEntity<String> put(@RequestBody BaseDto baseDto, @PathVariable UUID id) {
        return baseService.put(baseDto, id);
    }

    /**
     * Сделать существующий Base visible.FALSE (РАБОТАЕТ)
     */
    @DeleteMapping("/delete/{id}")
    @PreAuthorize(value = "@authService.authInfo.hasRole('ADMIN')")
    public ResponseEntity<String> deleteBase(@PathVariable UUID id) {
        return baseService.setVisibleFalse(id);
    }

    /**
     * Достать все Base (РАБОТАЕТ)
     */
    @GetMapping("/")
    public ResponseEntity<List<Base>> getBases() {
        return baseService.getVisibleBases();
    }

    //TODO Как найти все Base которые торгуют определенным gasType?
    //TODO Как найти все gasType которыми торгует определенный Base?
}
