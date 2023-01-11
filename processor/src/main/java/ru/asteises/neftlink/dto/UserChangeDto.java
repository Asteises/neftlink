package ru.asteises.neftlink.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
public class UserChangeDto {

    @Max(value = 30, message = "surname must be 30 characters maximum")
    protected String surname;

    @Max(value = 15, message = "firstname must be 15 characters maximum")
    private String firstname;

    @Max(value = 25, message = "lastname must be 25 characters maximum")
    private String lastname;

    //TODO Нужна валидация телефонного номера;
    //TODO При изменении номера телефона потребуется ввести код из смс с нового номера;

    @Min(value = 10, message = "phone must be 10 characters minimum")
    @Max(value = 10, message = "phone must be 10 characters minimum")
    private String phone;

    //TODO При изменении имейл потребуется ввести код из письма с нового имейл;
    @Max(value = 100, message = "email must be 100 characters maximum")
    private String email;

    @Min(value = 12, message = "inn must be 12 characters minimum")
    @Max(value = 12, message = "inn must be 12 characters minimum")
    private Integer inn;

    @Min(value = 6, message = "password must be 6 characters minimum")
    @Max(value = 50, message = "password must be 50 characters minimum")
    private String password;
}
