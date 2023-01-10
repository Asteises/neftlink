package ru.asteises.userclient.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

/**
 * DTO для проверки введенных пользователем данных при регистрации. Плюс приветственное сообщение.
 */
@Getter
@Setter
public class RegistrationViewDto {

    private String greetings;
    private String phone;
    private Email email;
}
