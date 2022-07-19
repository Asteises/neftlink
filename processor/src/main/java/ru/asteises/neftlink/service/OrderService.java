package ru.asteises.neftlink.service;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.asteises.neftlink.dto.OrderDto;
import ru.asteises.neftlink.entity.Order;
import ru.asteises.neftlink.mapper.OrderMapper;
import ru.asteises.neftlink.repositoryes.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Отвечает за всю бизнес-логику связанную с Order (все что может происходить с объектами типа Order)
 */
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final GasService gasService;
    private final BaseService baseService;

    /**
     *Создаем объект Order из OrderDto и сохраняем в базу данных
     */
    public String add(OrderDto orderDto) {
        Order order = OrderMapper.INSTANCE.toOrder(orderDto, userService, gasService, baseService);
        orderRepository.save(order);
        return "заказ успешно добавлен в репозиторий";
    }

    /**
     * Находим объект Order по ID, меняем цену и время обновлекния
     */
    public ResponseEntity<String> put(Long price, UUID orderId, UUID userId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if (order.getUser().getId().equals(userId)) {
                order.setCost(price);
                order.setUpdateDate(LocalDateTime.now());
                orderRepository.save(order);
                return ResponseEntity.ok("Order.Cost успешно обновлена");
            } else {
                return ResponseEntity.ok("Order не принадлежит данному User");
            }
        }
        return ResponseEntity.ok("мы не нашли подходящий Order");
    }

    /**
     * Меняем order visible
     */
    public ResponseEntity<String> setVisibleFalse(UUID orderId, UUID userId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if (order.getUser().getId().equals(userId)) {
                order.setVisible(Boolean.FALSE);
                orderRepository.save(order);
                order.setUpdateDate(LocalDateTime.now());
                orderRepository.save(order);
                return ResponseEntity.ok("Order.Cost успешно обновлена");
            } else {
                return ResponseEntity.ok("Order не принадлежит данному User");
            }
        }
        return ResponseEntity.ok("Мы не нашли подходящй order");
    }

    /**
     * Доставем все order
     */
    public ResponseEntity<List<Order>> getVisibleOrders() {
        List<Order> orders = orderRepository.findAllByVisibleTrue();
        return ResponseEntity.ok(orders);
    }

    public ResponseEntity<List<Order>> getOrdersByCost(Long from) {
        List<Order> orders = orderRepository.findOrdersByCostGreaterThan(from);
        return ResponseEntity.ok(orders);
    }

    public ResponseEntity<Order> getOrderByCost(Long cost) {
        Order order = orderRepository.findOrderByCost(cost);
        return ResponseEntity.ok(order);
    }
}
