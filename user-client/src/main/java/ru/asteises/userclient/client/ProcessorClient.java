package ru.asteises.userclient.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.asteises.userclient.dto.UserDto;
import ru.asteises.userclient.dto.RegistrationViewDto;

import java.util.List;

/**
 * Класс для обмена данными с микросервисом Processor.
 */
@Slf4j
@Component
public class ProcessorClient {

    private final String docker = "${ewm_stats_service_url}";
    private final String localhost = "http://localhost:9090";

    private final RestTemplate restTemplate;

    @Autowired
    public ProcessorClient(@Value(localhost) String uri, RestTemplateBuilder builder) {
        this.restTemplate = builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(uri))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build();
    }

    /**
     * Метод для передачи регистрационных данных в Processor.
     *
     * @param patch Путь
     * @param userDto #{@link UserDto}
     * @return RegistrationViewDto #{@link RegistrationViewDto}
     */
    public ResponseEntity<RegistrationViewDto> sendRegistrationData(String patch, UserDto userDto) {
        HttpEntity<UserDto> requestEntity = new HttpEntity<>(userDto, defaultHeaders());
        return restTemplate.exchange(patch, HttpMethod.POST, requestEntity, RegistrationViewDto.class);
    }

    private HttpHeaders defaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        return headers;
    }
}
