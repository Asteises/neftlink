package ru.asteises.neftlink.mapper;

import org.springframework.stereotype.Service;
import ru.asteises.neftlink.dto.OrderDto;
import ru.asteises.neftlink.entity.Order;
import ru.asteises.neftlink.service.BaseService;
import ru.asteises.neftlink.service.GasService;
import ru.asteises.neftlink.service.UserService;

import java.time.LocalDateTime;

/**
 * Преобразует Order в OrderDto и обратно
 */

@Service
public class OrderMapper {

    private final BaseService baseService;
    private final GasService gasService;
    private final UserService userService;

    public OrderMapper(BaseService baseService, GasService gasService, UserService userService) {
        this.baseService = baseService;
        this.gasService = gasService;
        this.userService = userService;
    }

    public Order orderDtoToOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setCost(orderDto.getCost());
        order.setCreateDate(LocalDateTime.now());
        order.setUpdateDate(LocalDateTime.now());
        order.setBase(baseService.getBaseByName(orderDto.getBaseName()));
        order.setGas(gasService.getGasByName(orderDto.getGasType()));
        order.setUser(userService.getUserByInn(orderDto.getInn()));
        return order;
    }

    public OrderDto orderToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setCost(order.getCost());
        orderDto.setCreateDate(order.getCreateDate());
        orderDto.setUpdateDate(order.getUpdateDate());
        return orderDto;
    }

    //TODO Создать Order найдя в репозитории Base (через Service) нужный Base используя findByName() и найденный добавить в Order
    //TODO Создать Order найдя в репозитории Gas (через Service) нужный Gas используя findByGasType() и найденный добавить в Order
    //TODO И сохранить в базу в таблицу order
}
