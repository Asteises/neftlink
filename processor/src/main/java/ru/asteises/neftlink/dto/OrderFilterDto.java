package ru.asteises.neftlink.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderFilterDto {

    private Long costFrom;
    private Long costTo;
    private String gasType;
    private String baseName;
    private Integer inn;

}
