package ru.asteises.userclient.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

/**
 * Общий вид DTO для регистрации пользователей.
 */
@Getter
@Setter
public abstract class RegistrationDto {

    @NotNull(message = "surname is required")
    @NotBlank(message = "surname is required")
    @Max(value = 30, message = "surname must be 30 characters maximum")
    protected String surname;

    @NotNull(message = "firstname is required")
    @NotBlank(message = "firstname is required")
    @Max(value = 15, message = "firstname must be 15 characters maximum")
    private String firstname;

    @NotNull(message = "lastname is required")
    @NotBlank(message = "lastname is required")
    @Max(value = 25, message = "lastname must be 25 characters maximum")
    private String lastname;

    //TODO Нужна валидация телефонного номера;

    @NotNull(message = "phone is required")
    @NotBlank(message = "phone is required")
    private String phone;

    @NotNull(message = "email is required")
    @NotBlank(message = "email is required")
    @Max(value = 100, message = "email must be 100 characters maximum")
    private Email email;

    @NotNull(message = "password is required")
    @NotBlank(message = "password is required")
    @Min(value = 6, message = "password must be 6 characters minimum")
    @Max(value = 50, message = "password must be 50 characters minimum")
    private String password;

    //TODO Как сделать проверку введенного пароля?

    @NotNull(message = "passwordRepeat is required")
    @NotBlank(message = "passwordRepeat is required")
    @Min(value = 6, message = "password must be 6 characters minimum")
    @Max(value = 50, message = "password must be 50 characters minimum")
    private String passwordRepeat;
}
