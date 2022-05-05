package ru.asteises.neftlink.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Данный класс упрощает основные сущности, убирает из них все автоматизированные процессы;
 */

@Getter
@Setter
public class OrderDto {

    private Double cost;
    private LocalDateTime updateDate;
    private LocalDateTime createDate;
    private String gasType;
    private String baseName;
}
