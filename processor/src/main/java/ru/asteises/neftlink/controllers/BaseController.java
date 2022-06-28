package ru.asteises.neftlink.controllers;

import org.springframework.http.ResponseEntity;
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
     *Принимает запрос на создание нового объекта типа Gas в базу данных с помощью Service
     */
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody BaseDto baseDto) {
        baseService.add(baseDto);
        return ResponseEntity.ok("запрос на создание нового объекта Base в базу данных успешно принят и обработан");
    }

    @PutMapping("/change/{id}")
    public ResponseEntity<String> put(@RequestBody BaseDto baseDto, @PathVariable UUID id) {
        return baseService.put(baseDto, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBase(@PathVariable UUID id) {
        return baseService.setVisibleFalse(id);
    }

    @GetMapping("/")
    public ResponseEntity<List<Base>> getBases() {
        return baseService.getVisibleBases();
    }

}
