package ru.asteises.neftlink.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.asteises.neftlink.enums.OrderSortEnum;
import ru.asteises.neftlink.enums.SortEnum;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderFilterDto {

    private Long[] ids;
    private Long costFrom;
    private Long costTo;
    private String gasType;
    private String baseName;
    private Integer inn;
    private SortEnum sortEnum;
    private OrderSortEnum orderSortEnum;

}
