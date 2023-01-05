package ru.asteises.neftlink.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Данный класс упрощает основные сущности, убирает из них все автоматизированные процессы;
 */

@Setter
@Getter
public class BaseDto {

    private String name;
    private String address;
}
