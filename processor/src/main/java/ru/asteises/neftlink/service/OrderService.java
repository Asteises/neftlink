package ru.asteises.neftlink.service;

import org.springframework.stereotype.Service;
import ru.asteises.neftlink.dto.OrderDto;
import ru.asteises.neftlink.entity.Order;
import ru.asteises.neftlink.mapper.OrderMapper;
import ru.asteises.neftlink.repositoryes.OrderRepository;

import javax.transaction.Transactional;

/**
 * Отвечает за всю бизнес-логику связанную с Order (все что может происходить с объектами типа Order)
 */
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    /**
     *Создаем объект Order из OrderDto и сохраняем в базу данных
     */
    @Transactional
    public String add(OrderDto orderDto) {
        Order order = orderMapper.orderDtoToOrder(orderDto);
        orderRepository.save(order);
        return "заказ успешно добавлен в репозиторий";
    }
}
