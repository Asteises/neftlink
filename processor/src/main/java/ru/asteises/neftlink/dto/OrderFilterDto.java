package ru.asteises.neftlink.dto;

import lombok.Getter;
import lombok.Setter;
import ru.asteises.neftlink.enums.OrderSortEnum;
import ru.asteises.neftlink.enums.SortEnum;

@Getter
@Setter
public class OrderFilterDto {

    private Long costFrom;
    private Long costTo;
    private String gasType;
    private String baseName;
    private Integer inn;
    private SortEnum sortEnum;
    private OrderSortEnum orderSortEnum;

}
