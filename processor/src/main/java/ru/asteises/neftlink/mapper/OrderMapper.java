package ru.asteises.neftlink.mapper;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ru.asteises.neftlink.dto.OrderDto;
import ru.asteises.neftlink.entity.Order;

/**
 * Преобразует Order в OrderDto и обратно
 */

@Service
public class OrderMapper {

    public Order orderDtoToOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setCost(orderDto.getCost());
        order.setCreateDate(orderDto.getCreateDate());
        order.setUpdateDate(orderDto.getUpdateDate());
        return order;
    }

    public OrderDto orderToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setCost(order.getCost());
        orderDto.setCreateDate(order.getCreateDate());
        orderDto.setUpdateDate(order.getUpdateDate());
        return orderDto;
    }
}
