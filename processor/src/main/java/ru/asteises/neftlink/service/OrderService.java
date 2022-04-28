package ru.asteises.neftlink.service;

import org.springframework.stereotype.Service;
import ru.asteises.neftlink.repositoryes.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
