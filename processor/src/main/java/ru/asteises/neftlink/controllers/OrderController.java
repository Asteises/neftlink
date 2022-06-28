package ru.asteises.neftlink.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.asteises.neftlink.dto.OrderDto;
import ru.asteises.neftlink.service.OrderService;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     *Принимает запрос на создание нового объекта типа Order в базу данных с помощью Service
     */
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody OrderDto orderDto) {
        orderService.add(orderDto);
        return ResponseEntity.ok("запрос на создание нового объекта Order в базу данных успешно принят и обработан");
    }

    @PutMapping("/change/{id}")
    public ResponseEntity<String> put(@RequestParam Long price, @PathVariable UUID id) {
        return orderService.put(price, id);
    }
}
