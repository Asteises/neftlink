package ru.asteises.neftlink.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
import ru.asteises.neftlink.service.AuthService;
import ru.asteises.neftlink.service.OrderService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final AuthService authService;

    /**
     *Принимает запрос на создание нового объекта типа Order в базу данных с помощью Service (РАБОТАЕТ)
     */
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody OrderDto orderDto) {
        orderService.add(orderDto);
        return ResponseEntity.ok("запрос на создание нового объекта Order в базу данных успешно принят и обработан");
    }

    /**
     * Меняем cost в Order по id (РАБОТАЕТ)
     */
    @PutMapping("/change/{orderId}")
    public ResponseEntity<String> put(@PathVariable UUID orderId,
                                      @RequestParam Long price,
                                      @RequestParam UUID userId
                                      ) {
        return orderService.put(orderId, price, userId);
    }

    /**
     * Удалить Order по id (РАБОТАЕТ)
     */
    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<String> delete(@PathVariable UUID orderId,
                                         @RequestParam UUID userId) {
        return orderService.setVisibleFalse(orderId, userId);
    }

    /**
     * Достать все visible.TRUE Order (РАБОТАЕТ)
     */
    @GetMapping("/")
    public ResponseEntity<Page<Order>> getOrders() {
        return orderService.getVisibleOrders();
    }

    /**
     * Достать все Order по cost ОТ и ДО (РАБОТАЕТ)
     */
    @GetMapping("/search/")
    public ResponseEntity<List<OrderDto>> getByCost(@RequestParam Long from,
                                                 Long to) {
        return orderService.getOrdersByCost(from, to);
    }

    /**
     * Достать все Order по gasType (РАБОТАЕТ)
     */
    @GetMapping("/search/gas/")
    public ResponseEntity<List<Order>> getByGas(@RequestParam String gasType) {
        return orderService.getOrdersByGas(gasType);
    }


    /**
     * Достать все Order по baseName (РАБОТАЕТ)
     */
    @GetMapping("/search/base/")
    public ResponseEntity<List<Order>> getByBase(@RequestParam String baseName) {
        return orderService.getOrdersByBase(baseName);
    }

    /**
     * Достать все Order по userId (РАБОТАЕТ)
     */
    @GetMapping("/search/user/")
    public ResponseEntity<List<Order>> getByUser(@RequestParam UUID userId) {
        return orderService.getOrdersByUser(userId);
    }
}
