package ru.asteises.userclient.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * DTO для регистрации пользователя в роли производителя.
 * <p>
 * Производителем может быть только юридическое лицо.
 * ИНН - 10 символов.
 */
@Getter
@Setter
public class UserManufacturerUserDto extends UserDto {

    @NotNull(message = "inn is required")
    @Min(value = 10, message = "inn need 10 numbers")
    @Max(value = 10, message = "inn need 10 numbers")
    private Integer inn;
}
