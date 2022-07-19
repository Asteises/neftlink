package ru.asteises.neftlink.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.asteises.neftlink.dto.OrderDto;
import ru.asteises.neftlink.entity.Order;
import ru.asteises.neftlink.service.OrderService;

import java.util.List;
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

    @PutMapping("/change/{orderId}")
    public ResponseEntity<String> put(@RequestParam Long price,
                                      UUID userId,
                                      @PathVariable UUID orderId) {
        return orderService.put(price, orderId, userId);
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<String> delete(@PathVariable UUID orderId,
                                         @RequestParam UUID userId) {
        return orderService.setVisibleFalse(orderId, userId);
    }

    @GetMapping("/")
    public ResponseEntity<List<Order>> getOrders() {
        return orderService.getVisibleOrders();
    }

    @GetMapping("/search/")
    public ResponseEntity<List<Order>> getByCost(@RequestParam Long from) {
        return orderService.getOrdersByCost(from);
    }

//    @GetMapping("/search/")
//    public ResponseEntity<Order> getOrderByCost(@RequestParam Long cost) {
//        return orderService.getOrderByCost(cost);
//    }
}
