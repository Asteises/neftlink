package ru.asteises.neftlink.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.asteises.neftlink.dto.OrderDto;
import ru.asteises.neftlink.entity.Order;
import ru.asteises.neftlink.service.OrderService;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<Order>> getByCost(@RequestParam Long from,
                                                 Long to) {
        List<Order> orders = orderService.getVisibleOrders().getBody();
        orders.stream().filter(order -> order.getCost() >= from && order.getCost() <= to).collect(Collectors.toList());
        return (ResponseEntity<List<Order>>) orders;
    }

    @GetMapping("/search/cost")
    public ResponseEntity<List<Order>> getByMinCost() {
        List<Order> orders = orderService.getVisibleOrders().getBody();
        return (ResponseEntity<List<Order>>) orders.stream()
                .sorted(Comparator.comparingLong(Order::getCost))
                .collect(Collectors.toList());
    }

    @GetMapping("/search/cost")
    public ResponseEntity<List<Order>> getByMaxCost() {
        List<Order> orders = orderService.getVisibleOrders().getBody();
        return (ResponseEntity<List<Order>>) orders.stream()
                .sorted(Comparator.comparingLong(Order::getCost).reversed())
                .collect(Collectors.toList());
    }

    @GetMapping("/search/date")
    public ResponseEntity<List<Order>> getByDate(@RequestParam LocalDateTime from
            , LocalDateTime to) {
        List<Order> orders = orderService.getVisibleOrders().getBody();
        return (ResponseEntity<List<Order>>) orders.stream()
                .filter(date -> date.getUpdateDate().isAfter(from) && date.getUpdateDate().isBefore(to))
                .collect(Collectors.toList());
    }
}
