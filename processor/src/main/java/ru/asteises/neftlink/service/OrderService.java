package ru.asteises.neftlink.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.asteises.neftlink.dto.OrderDto;
import ru.asteises.neftlink.entity.Order;
import ru.asteises.neftlink.mapper.OrderMapper;
import ru.asteises.neftlink.repositoryes.OrderRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Отвечает за всю бизнес-логику связанную с Order (все что может происходить с объектами типа Order)
 */
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    /**
     *Создаем объект Order из OrderDto и сохраняем в базу данных
     */
    public String add(OrderDto orderDto) {
        Order order = orderMapper.orderDtoToOrder(orderDto);
        orderRepository.save(order);
        return "заказ успешно добавлен в репозиторий";
    }

    /**
     * Находим объект Order по ID, меняем цену и время обновлекния
     */
    public ResponseEntity<String> put(Long price, UUID id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setCost(price);
            order.setUpdateDate(LocalDateTime.now());
            orderRepository.save(order);
            return ResponseEntity.ok("Order.Cost успешно обновлена");
        }
        return ResponseEntity.ok("мы не нашли подходящий Order");
    }
}
