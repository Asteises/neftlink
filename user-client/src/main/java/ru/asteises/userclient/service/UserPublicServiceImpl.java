package ru.asteises.userclient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.asteises.userclient.client.ProcessorClient;
import ru.asteises.userclient.dto.UserDto;
import ru.asteises.userclient.dto.RegistrationViewDto;

@Service
@RequiredArgsConstructor
public class UserPublicServiceImpl implements UserPublicService {

    private ProcessorClient processorClient;

    /**
     * Бизнес логика для обмена данными при регистрации.
     *
     * @param userDto #{@link UserDto}
     * @return RegistrationViewDto #{@link RegistrationViewDto}
     */
    @Override
    public ResponseEntity<RegistrationViewDto> userRegistration(UserDto userDto) {
        return processorClient.sendRegistrationData("/registration", userDto);
    }
}
