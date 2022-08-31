package ru.asteises.neftlink.repositoryes;

import ru.asteises.neftlink.dto.OrderFilterDto;
import ru.asteises.neftlink.entity.Order;

import java.util.List;

public interface FindOrderByFilter {

    List<Order> getOrdersByFilter(OrderFilterDto orderFilterDto, int elements, int shift);

    long countByFilter(OrderFilterDto orderFilterDto);

}
