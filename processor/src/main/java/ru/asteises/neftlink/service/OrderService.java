package ru.asteises.neftlink.service;

import org.springframework.stereotype.Service;
import ru.asteises.neftlink.dto.OrderDto;
import ru.asteises.neftlink.mapper.OrderMapper;
import ru.asteises.neftlink.repositoryes.OrderRepository;

/**
 * Отвечает за всю бизнес-логику связанную с Order (все что может происходить с объектами типа Order)
 */
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    /**
     *Создаем объект Order из OrderDto и сохраняем в базу данных
     */
    public String add(OrderDto orderDto) {
        orderRepository.save(orderMapper.orderDtoToOrder(orderDto));
        return "заказ успешно добавлен в репозиторий";
    }
}
