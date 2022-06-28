package ru.asteises.neftlink.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.asteises.neftlink.dto.GasDto;
import ru.asteises.neftlink.entity.Gas;
import ru.asteises.neftlink.service.AuthService;
import ru.asteises.neftlink.service.GasService;

import java.util.List;
import java.util.UUID;

/**
 * Контроллер - принимамет и отвечает на запросы
 */
@RestController
@RequestMapping("/gas")
@RequiredArgsConstructor
public class GasController {

    private final GasService gasService;

    private final AuthService authService;

    /**
     * Принимает запрос на создание нового объекта типа Gas в базу данных с помощью Service
     */
    @PostMapping("/add")
    @PreAuthorize(value = "@authService.authInfo.hasRole('ADMIN')")
    public ResponseEntity<String> add(@RequestBody GasDto gasDto) {
        gasService.add(gasDto);
        return ResponseEntity.ok("запрос на создание нового объекта Gas в базу данных успешно принят и обработан");
    }

    /**
     * Принимаем запрос на замену gas по id
     */
    @PutMapping("/change/{id}")
    public ResponseEntity<String> put(@RequestBody GasDto gasDto, UUID id) {
        return gasService.put(gasDto, id);
    }

    //TODO Сделать удаление и получение всех visible gas

    /**
     * Принимаем запрос на удаление gas, по факте изменяем visible gas для пользователя
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteGas(@PathVariable UUID id) {
        return gasService.setVisibleFalse(id);
    }

    /**
     * Принимаем запрос на вывод всех visible gas
     */
    @GetMapping("/")
    public ResponseEntity<List<Gas>> getGases() {
        return gasService.getVisibleGases();
    }
}
