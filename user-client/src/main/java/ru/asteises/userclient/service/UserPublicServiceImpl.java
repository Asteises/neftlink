package ru.asteises.userclient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.asteises.userclient.client.ProcessorClient;
import ru.asteises.userclient.dto.RegistrationDto;
import ru.asteises.userclient.dto.RegistrationViewDto;

@Service
@RequiredArgsConstructor
public class UserPublicServiceImpl implements UserPublicService {

    private ProcessorClient processorClient;

    /**
     * Бизнес логика для обмена данными при регистрации.
     *
     * @param registrationDto #{@link RegistrationDto}
     * @return RegistrationViewDto #{@link RegistrationViewDto}
     */
    @Override
    public ResponseEntity<RegistrationViewDto> userRegistration(RegistrationDto registrationDto) {
        return processorClient.sendRegistrationData("/registration", registrationDto);
    }
}
