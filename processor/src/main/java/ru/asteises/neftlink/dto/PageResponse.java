package ru.asteises.neftlink.dto;

import lombok.Getter;
import lombok.Setter;
import ru.asteises.neftlink.entity.Order;

import java.util.List;

@Getter
@Setter
public class PageResponse {

    private List<Order> orderList;
    private PageInfo pageInfo;

}
