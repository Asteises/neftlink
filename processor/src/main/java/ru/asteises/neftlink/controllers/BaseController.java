package ru.asteises.neftlink.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.asteises.neftlink.dto.BaseDto;
import ru.asteises.neftlink.repositoryes.BaseRepository;
import ru.asteises.neftlink.service.BaseService;

@RestController
@RequestMapping("/base")
public class BaseController {

    private BaseService baseService;

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

}
