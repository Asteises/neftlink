package ru.asteises.neftlink.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.asteises.neftlink.dto.OrderDto;
import ru.asteises.neftlink.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     *Принимает запрос на создание нового объекта типа Order в базу данных с помощью Service
     */
    @PostMapping("/add")
    public ResponseEntity<String> add(OrderDto orderDto) {
        orderService.add(orderDto);
        return ResponseEntity.ok("запрос на создание нового объекта Order в базу данных успешно принят и обработан");
    }

    //TODO Сделать добавление Order
}
