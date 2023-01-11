package ru.asteises.userclient.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.asteises.userclient.dto.UserDto;
import ru.asteises.userclient.dto.RegistrationViewDto;

@Service
public interface UserPublicService {

    /**
     * Бизнес логика для обмена данными при регистрации.
     *
     * @param userDto #{@link UserDto}
     * @return RegistrationViewDto #{@link RegistrationViewDto}
     */
    ResponseEntity<RegistrationViewDto> userRegistration(@RequestBody UserDto userDto);
}
