package ru.asteises.neftlink.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Данный класс упрощает основные сущности, убирает из них все автоматизированные процессы;
 */

@Setter
@Getter
public class UserDto {

    private Integer inn;
    private String company;
    private String email;
    private String password;
    private String name;
}
