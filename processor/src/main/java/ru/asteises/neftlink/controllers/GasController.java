package ru.asteises.neftlink.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.asteises.neftlink.dto.GasDto;
import ru.asteises.neftlink.service.GasService;

//Контроллер - принимамет и отвечает на запросы
@RestController
@RequestMapping("/gas")
public class GasController {

    private GasService gasService;

    public GasController(GasService gasService) {
        this.gasService = gasService;
    }

    /**
     *ринимает запрос на создание нового объекта типа Gas в базу данных
     */
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody GasDto gasDto) {
        gasService.add(gasDto);
        return ResponseEntity.ok("запрос успешно принят и обработан");
    }


}
